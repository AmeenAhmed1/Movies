package com.ameen.movies.domain.usecase

import androidx.paging.PagingData
import com.ameen.movies.data.model.MovieModel
import com.ameen.movies.domain.repository.TopRatedMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(private val repo: TopRatedMovieRepository) {
    fun execute(): Flow<PagingData<MovieModel>> {
        return repo.getTopRatedMovies()
    }
}