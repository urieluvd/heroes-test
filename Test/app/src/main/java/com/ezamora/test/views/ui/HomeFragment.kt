package com.ezamora.test.views.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ezamora.test.databinding.FragmentHomeBinding
import com.ezamora.test.domain.CharacterResponse
import com.ezamora.test.viewmodels.HomeViewModel
import com.ezamora.test.views.adapters.HeroesAdapter
import com.ezamora.test.views.adapters.HeroesAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(), HeroesAdapterListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    // private lateinit var viewModel: HomeViewModel
    private lateinit var viewModel : HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter : HeroesAdapter
    private var heroes : CharacterResponse? = null
    private lateinit var layoutManager: LinearLayoutManager

    private var pastVisiblesItems : Int? = 0
    private var visibleItemCount : Int? = 0
    private var totalItemCount : Int? = 0

    private var offset = 0
    private var load : Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        adapter = HeroesAdapter(this@HomeFragment)
        setListeners()
        viewModel.heroesData.observe(viewLifecycleOwner, ::heroesDataObserve)
        viewModel.getCharacters(offset)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
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
                                viewModel.getCharacters(offset)
                            } else {
                                load = true
                            }
                        }
                    }
                }
            })
        }
    }

    private fun heroesDataObserve(data: CharacterResponse?){
        load = true
        binding.clLoading.visibility = View.GONE
        if (data != null){
            heroes = data
            adapter.heroesList.addAll(data.data.results)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onHeroClicked(character: com.ezamora.test.domain.Character) {
        Timber.i("Hero: $character")

        val nav = findNavController()
        val characterId = character.id

        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(characterId)
        nav.navigate(action)

    }

}