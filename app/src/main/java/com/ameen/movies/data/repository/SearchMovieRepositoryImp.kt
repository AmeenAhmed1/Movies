package com.ameen.movies.data.repository

import com.ameen.movies.core.util.API_KEY
import com.ameen.movies.core.wrapper.ResultWrapper
import com.ameen.movies.data.mapper.DataModelMapper
import com.ameen.movies.data.remote.MoviesApi
import com.ameen.movies.domain.model.MovieData
import com.ameen.movies.domain.repository.SearchMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMovieRepositoryImp @Inject constructor(
    private val api: MoviesApi,
    private val dataModelMapper: DataModelMapper
) : SearchMovieRepository {

    override fun searchMovie(
        searchQuery: String
    ): Flow<ResultWrapper<List<MovieData>>> {
        return flow {
            try {
                val response = api.searchMovie(API_KEY, searchQuery, 1)
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResultWrapper.Success(dataModelMapper.movieDataResponseToViewState(it)))
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