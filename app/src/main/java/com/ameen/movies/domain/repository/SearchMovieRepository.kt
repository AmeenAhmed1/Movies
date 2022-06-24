package com.ameen.movies.domain.repository

import com.ameen.movies.core.wrapper.ResultWrapper
import com.ameen.movies.domain.model.MovieData
import kotlinx.coroutines.flow.Flow

interface SearchMovieRepository {
    fun searchMovie(searchQuery: String): Flow<ResultWrapper<List<MovieData>>>
}