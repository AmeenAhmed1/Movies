package com.ameen.movies.presentation.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameen.movies.core.wrapper.ResultWrapper
import com.ameen.movies.domain.model.MovieData
import com.ameen.movies.domain.model.MovieGenre
import com.ameen.movies.domain.usecase.GetMovieGenreUseCase
import com.ameen.movies.domain.usecase.GetTopRatedMoviesUseCase
import com.ameen.movies.domain.usecase.SearchMoviesUseCase
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
    private val getMovieGenreUseCase: GetMovieGenreUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    private val _movieGenreList: MutableStateFlow<ResultWrapper<List<MovieGenre>>> =
        MutableStateFlow(ResultWrapper.Loading)
    val movieGenreList = _movieGenreList


    private val _movieDataList: MutableStateFlow<ResultWrapper<List<MovieData>>> =
        MutableStateFlow(ResultWrapper.Loading)
    val movieDataList = _movieDataList


    fun getTopRatedMovies() =
        getTopRatedMoviesUseCase.execute().flowOn(Dispatchers.IO)
            .onEach {
                _movieDataList.emit(it)
            }.launchIn(viewModelScope)

    fun getMovieGenre() =
        getMovieGenreUseCase.execute().flowOn(Dispatchers.IO)
            .onEach {
                _movieGenreList.emit(it)
            }.launchIn(viewModelScope)


    fun searchMovies(searchQuery: String) =
        searchMoviesUseCase.execute(searchQuery).flowOn(Dispatchers.IO)
            .onEach {
                _movieDataList.emit(it)
            }.launchIn(viewModelScope)

}