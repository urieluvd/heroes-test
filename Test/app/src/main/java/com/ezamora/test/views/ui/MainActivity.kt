package com.ezamora.test.views.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ezamora.test.R
import com.ezamora.test.databinding.ActivityMainBinding
import com.ezamora.test.viewmodels.HeroesViewModel
import com.ezamora.test.views.adapters.HeroesAdapter
import com.ezamora.test.views.models.HeroesModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel : HeroesViewModel by viewModels()

    private lateinit var adapter : HeroesAdapter
    private var heroesModel : HeroesModel? = null
    private lateinit var layoutManager: LinearLayoutManager

    private var pastVisiblesItems : Int? = 0
    private var visibleItemCount : Int? = 0
    private var totalItemCount : Int? = 0

    private var offset = 0
    private var load : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = HeroesAdapter()
        setListeners()
        viewModel.heroesData.observe(this, ::heroesDataObserve)
        viewModel.getCharacteres(offset)
    }

    private fun setListeners() {
        layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        with(binding){
            this.rvSuperHeroes.let {
                it.adapter = adapter
                it.layoutManager = layoutManager
            }
            
            this.rvSuperHeroes.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0){
                        visibleItemCount = layoutManager.childCount
                        totalItemCount = layoutManager.itemCount
                        pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()

                        if (load){
                            if ((visibleItemCount!! + pastVisiblesItems!!) >= totalItemCount!!){
                                load = false
                                offset += 20
                                binding.clLoading.visibility = View.VISIBLE
                                viewModel.getCharacteres(offset)
                            } else {
                                load = true
                            }
                        }
                    }
                }
            })
        }
    }

    private fun heroesDataObserve(data: HeroesModel?){
        load = true
        binding.clLoading.visibility = View.GONE
        if (data != null){
            heroesModel = data
            adapter.heroesList.addAll(data.data.results)
            adapter.notifyDataSetChanged()
        }
    }
}