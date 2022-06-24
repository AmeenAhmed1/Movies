package com.ameen.movies.domain.usecase

import com.ameen.movies.core.wrapper.ResultWrapper
import com.ameen.movies.domain.model.MovieData
import com.ameen.movies.domain.repository.SearchMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(private val repo: SearchMovieRepository) {
    fun execute(searchQuery: String): Flow<ResultWrapper<List<MovieData>>> {
        return repo.searchMovie(searchQuery)
    }
}