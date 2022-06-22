package com.ameen.movies.data.repository

import com.ameen.movies.core.util.API_KEY
import com.ameen.movies.core.wrapper.ResultWrapper
import com.ameen.movies.data.mapper.MovieGenreDataMapper
import com.ameen.movies.data.remote.MoviesApi
import com.ameen.movies.domain.model.MovieGenre
import com.ameen.movies.domain.repository.MoviesGenresRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesGenresRepositoryImp @Inject constructor(
    private val api: MoviesApi,
    private val movieGenreDataMapper: MovieGenreDataMapper
) : MoviesGenresRepository {

    override fun getMovieGenres(): Flow<ResultWrapper<List<MovieGenre>>> {
        return flow {
            try {
                val response = api.getMovieGenres(API_KEY)
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(
                            ResultWrapper.Success(
                                movieGenreDataMapper.movieGenreResponseToViewState(it)
                            )
                        )
                    } ?: emit(ResultWrapper.Error("Something Happen Please Try Again!!"))
                } else {
                    emit(ResultWrapper.Error(response.errorBody().toString()))
                }
            } catch (e: Exception) {
                emit(ResultWrapper.Error(e.message.toString()))
            }
        }
    }
}