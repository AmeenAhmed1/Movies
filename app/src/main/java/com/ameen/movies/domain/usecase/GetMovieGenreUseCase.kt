package com.ameen.movies.domain.usecase

import com.ameen.movies.core.wrapper.ResultWrapper
import com.ameen.movies.domain.model.MovieGenre
import com.ameen.movies.domain.repository.MoviesGenresRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieGenreUseCase @Inject constructor(private val repo: MoviesGenresRepository) {
    fun execute(): Flow<ResultWrapper<List<MovieGenre>>> {
        return repo.getMovieGenres()
    }
}