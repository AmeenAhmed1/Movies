package com.ameen.movies.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ameen.movies.core.util.RECYCLER_VIEW_GRID_SPAN_SIZE
import com.ameen.movies.core.wrapper.ResultWrapper
import com.ameen.movies.databinding.FragmentHomeBinding
import com.ameen.movies.domain.model.MovieData
import com.ameen.movies.domain.model.MovieGenre
import com.ameen.movies.presentation.adapter.HomeMovieAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private lateinit var recAdapter: HomeMovieAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    private var genreFilterId: Int = 0
    private lateinit var currentMovieList: List<MovieData>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObservers()

    }

    private fun initObservers() {

        lifecycleScope.launchWhenCreated {
            homeViewModel.movieDataList.collectLatest {
                when (it) {
                    is ResultWrapper.Success -> {
                        Log.e(TAG, "initObservers: $it")
                        currentMovieList = it.value
                        showMovieList(currentMovieList)
                    }

                    is ResultWrapper.Error -> {
                        Log.e(TAG, "initObservers: ${it.error}")
                    }
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            homeViewModel.movieGenreList.collect {
                when (it) {
                    is ResultWrapper.Success -> {
                        Log.e(TAG, "initObservers: $it")
                        createChip(it.value.toMutableList(), binding.movieGenreChips)
                    }

                    is ResultWrapper.Error -> {
                        Log.e(TAG, "initObservers: ${it.error}")
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {

        if (!this::recAdapter.isInitialized) {

            recAdapter = HomeMovieAdapter()

            binding.topMovieRecycler.apply {
                adapter = recAdapter
                layoutManager =
                    GridLayoutManager(requireContext(), RECYCLER_VIEW_GRID_SPAN_SIZE)
            }

            homeViewModel.getTopRatedMovies()

            recAdapter.onItemClicked {
                TODO("Selected Item Go To Details.")
            }

//            recAdapter.addLoadStateListener { loadState ->
//                if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading)
//                    binding.loadingProgress.show()
//                else {
//                    binding.loadingProgress.hide()
//
//                    val errorState = when {
//                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
//                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
//                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
//                        else -> null
//                    }
//                    errorState?.let {
//                        Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_LONG)
//                            .show()
//                    }
//                }
//            }

        } else
            homeViewModel.getTopRatedMovies()

    }

    private fun createChip(data: MutableList<MovieGenre>, chipView: ChipGroup) {
        data.add(0, MovieGenre(0, "All"))
        for (item in data) {
            val chip = Chip(requireContext())
            chip.text = item.name
            chip.isCheckable = true

            if (item.id == 0)
                chip.isChecked = true

            chipView.addView(chip)

        }

        binding.movieGenreChips.setOnCheckedStateChangeListener { group, checkedIds ->
            Log.e(TAG, "initObservers: Checked $checkedIds")
            Log.e(TAG, "createChip: CheckInListId: ${data[checkedIds.first().minus(1)]}")
            genreFilterId = data[checkedIds.first().minus(1)].id
            showMovieList(currentMovieList)
        }
    }

    private fun showMovieList(data: List<MovieData>) {
        if (genreFilterId != 0) {
            val list = data.filter { currentMovie ->
                currentMovie.genre_ids.contains(genreFilterId)
            }
            recAdapter.diffUtil.submitList(list)
        } else
            recAdapter.diffUtil.submitList(data)
    }

}