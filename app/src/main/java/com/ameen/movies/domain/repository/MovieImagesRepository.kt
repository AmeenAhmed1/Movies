package com.ameen.movies.domain.repository

import com.ameen.movies.core.wrapper.ResultWrapper
import com.ameen.movies.domain.model.MovieImages
import kotlinx.coroutines.flow.Flow


interface MovieImagesRepository {
    fun getMovieImages(id: Int): Flow<ResultWrapper<MovieImages>>
}