package com.ameen.movies.domain.usecase

import com.ameen.movies.core.wrapper.ResultWrapper
import com.ameen.movies.domain.model.MovieImages
import com.ameen.movies.domain.repository.MovieImagesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieImagesUseCase @Inject constructor(private val repo: MovieImagesRepository) {
    fun execute(movieId: Int): Flow<ResultWrapper<MovieImages>> {
        return repo.getMovieImages(movieId)
    }
}