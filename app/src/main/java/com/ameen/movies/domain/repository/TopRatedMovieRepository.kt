package com.ameen.movies.domain.repository

import androidx.paging.PagingData
import com.ameen.movies.data.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface TopRatedMovieRepository {
    fun getTopRatedMovies(): Flow<PagingData<MovieModel>>
}