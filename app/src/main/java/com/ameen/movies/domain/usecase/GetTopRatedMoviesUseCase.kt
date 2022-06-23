package com.ameen.movies.domain.usecase

import com.ameen.movies.core.wrapper.ResultWrapper
import com.ameen.movies.domain.model.MovieData
import com.ameen.movies.domain.repository.TopRatedMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(private val repo: TopRatedMovieRepository) {
    fun execute(): Flow<ResultWrapper<List<MovieData>>> {
        return repo.getTopRatedMovies()
    }
}