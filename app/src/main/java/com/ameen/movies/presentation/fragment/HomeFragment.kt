package com.ameen.movies.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.ameen.movies.core.util.RECYCLER_VIEW_GRID_SPAN_SIZE
import com.ameen.movies.databinding.FragmentHomeBinding
import com.ameen.movies.presentation.adapter.HomeMovieAdapter
import com.ameen.movies.presentation.extention.hide
import com.ameen.movies.presentation.extention.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private lateinit var recAdapter: HomeMovieAdapter
    private val homeViewModel: HomeViewModel by viewModels()

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
            homeViewModel.getTopRatedMovies().collectLatest { pagingData ->
                recAdapter.submitData(pagingData)
            }
        }

//        lifecycleScope.launchWhenCreated {
//            topRatedViewModel.topRatedMoviesList.collect() {
//                when (it) {
//                    is ResponseWrapper.Success -> recAdapter.diff.submitList(it.value.results)
//                    is ResponseWrapper.Fail -> {
//                        TODO("Handle Error && Failure")
//                    }
//                }
//            }
//        }
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

            recAdapter.addLoadStateListener { loadState ->
                if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading)
                    binding.loadingProgress.show()
                else {
                    binding.loadingProgress.hide()

                    val errorState = when {
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }
                    errorState?.let {
                        Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

        } else
            homeViewModel.getTopRatedMovies()

    }

}