package com.ameen.movies.data.remote

import com.ameen.movies.core.util.ApiEndPoints
import com.ameen.movies.data.model.MovieGenresResponse
import com.ameen.movies.data.model.TopRatedMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET(ApiEndPoints.TOP_MOVIES_ENDPOINT)
    suspend fun getTopRatedMoves(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int = 1
    ): Response<TopRatedMoviesResponse>


    @GET(ApiEndPoints.MOVIE_GENRES_ENDPOINT)
    suspend fun getMovieGenres(
        @Query("api_key") apiKey: String
    ): Response<MovieGenresResponse>

}