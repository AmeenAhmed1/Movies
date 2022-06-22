package com.ameen.movies.presentation.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ameen.movies.domain.usecase.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

//    private val _topRatedMovieList: MutableStateFlow<ResponseWrapper<TopRatedMoviesResponse>> =
//        MutableStateFlow()
//    val topRatedMoviesList = _topRatedMovieList

    fun getTopRatedMovies() =
        getTopRatedMoviesUseCase.execute().cachedIn(viewModelScope)

}