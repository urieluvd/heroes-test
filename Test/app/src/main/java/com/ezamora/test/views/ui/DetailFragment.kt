package com.ezamora.test.views.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ezamora.test.databinding.FragmentDetailBinding
import com.ezamora.test.viewmodels.DetailViewModel
import com.ezamora.test.views.adapters.ComicsRecyclerViewAdapter
import com.ezamora.test.views.adapters.SeriesRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.lang.Exception

@AndroidEntryPoint
class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by viewModels()
    private var isLoading = false

    private lateinit var comicsRecyclerViewAdapter: ComicsRecyclerViewAdapter
    private lateinit var seriesRecyclerViewAdapter: SeriesRecyclerViewAdapter

    val args: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val characterId = args.characterId

        comicsRecyclerViewAdapter = ComicsRecyclerViewAdapter()
        binding.comicsList.adapter = comicsRecyclerViewAdapter

        seriesRecyclerViewAdapter = SeriesRecyclerViewAdapter()
        binding.seriesList.adapter = seriesRecyclerViewAdapter

        viewModel.character.observe(viewLifecycleOwner) {
            Timber.i("Character $it")

            binding.name.text = it.name
            binding.description.text = it.description

            (activity as MainActivity).supportActionBar?.title = it.name

            val thumbnail = it.thumbnail.toString()
            Timber.d("Url: $thumbnail")
            Glide.with(binding.image.context)
                .load(thumbnail)
                .transition(DrawableTransitionOptions().crossFade())
                .into(binding.image)
            setLoadingState(false)
        }

        viewModel.comics.observe(viewLifecycleOwner) {
            Timber.i("Comics $it")
            comicsRecyclerViewAdapter.submitList(it)
            val size = comicsRecyclerViewAdapter.itemCount + 1
            comicsRecyclerViewAdapter.notifyItemRangeInserted(size, size + it.size)
        }


        viewModel.series.observe(viewLifecycleOwner) {
            Timber.i("Comics $it")
            seriesRecyclerViewAdapter.submitList(it)
            val size = seriesRecyclerViewAdapter.itemCount + 1
            seriesRecyclerViewAdapter.notifyItemRangeInserted(size, size + it.size)
        }



        try {
            characterId.let {
                setLoadingState(true)
                viewModel.setCharacterId(it)
                viewModel.getCharacter()
                viewModel.getComics()
                viewModel.getSeries()
            }
        } catch (exception: Exception){
            Toast.makeText(context, "There was an error.\nPlease try again ", Toast.LENGTH_LONG).show()
            Timber.e(exception)
            setLoadingState(false)
        }

    }


    private fun setLoadingState(loading : Boolean){
        if(loading){
            isLoading = true
            binding.progressBarLayout.visibility = View.VISIBLE
        } else {
            isLoading = false
            binding.progressBarLayout.visibility = View.INVISIBLE
        }
    }
}