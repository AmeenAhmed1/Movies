package com.ameen.movies.presentation.fragment.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ameen.movies.core.wrapper.ResultWrapper
import com.ameen.movies.databinding.FragmentDetailsBinding
import com.ameen.movies.presentation.adapter.MovieImagesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val TAG = "DetailsFragment"

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    private val args: DetailsFragmentArgs by navArgs()
    private val movieDataArgs by lazy {
        args.selectedMovieData
    }

    private val detailsFragmentViewModel: DetailsFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getMovieImages()
        initViews()
    }

    private fun initViews() {
        binding.movieDetailsTitle.text = movieDataArgs.title
        binding.movieOverview.text = movieDataArgs.overview
        binding.movieDate.text = "Release Date: ${movieDataArgs.release_date}"
        binding.movieRating.text = "Rating: ${movieDataArgs.vote_average}"

        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initImageSlider(data: List<String>) {
        binding.moviePhoto.adapter =
            MovieImagesAdapter(requireContext(), data, binding.loadingProgress)
    }

    private fun getMovieImages() {
        detailsFragmentViewModel.getMovieImages(movieDataArgs.id)
        lifecycleScope.launchWhenStarted {
            detailsFragmentViewModel.movieImageList.collect {
                when (it) {
                    is ResultWrapper.Success -> {
                        Log.e(TAG, "Images: $it")
                        initImageSlider(it.value.movieImages)
                    }

                    is ResultWrapper.Error -> {
                        Log.e(TAG, "initObservers: ${it.error}")
                    }
                }
            }
        }
    }

}