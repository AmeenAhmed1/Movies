package com.ameen.movies.presentation.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ameen.movies.core.wrapper.ResultWrapper
import com.ameen.movies.domain.model.MovieGenre
import com.ameen.movies.domain.usecase.GetMovieGenreUseCase
import com.ameen.movies.domain.usecase.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getMovieGenreUseCase: GetMovieGenreUseCase
) : ViewModel() {

    private val _movieGenreList: MutableStateFlow<ResultWrapper<List<MovieGenre>>> =
        MutableStateFlow(ResultWrapper.Loading)
    val movieGenreList = _movieGenreList

    init {
        getMovieGenre()
    }

    fun getTopRatedMovies() =
        getTopRatedMoviesUseCase.execute().cachedIn(viewModelScope)

    fun getMovieGenre() =
        getMovieGenreUseCase.execute().flowOn(Dispatchers.IO)
            .onEach {
                _movieGenreList.emit(it)
            }.launchIn(viewModelScope)


}