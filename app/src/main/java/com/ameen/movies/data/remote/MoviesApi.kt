package com.ameen.movies.data.remote

import com.ameen.movies.core.util.ApiEndPoints
import com.ameen.movies.data.model.MovieGenresResponse
import com.ameen.movies.data.model.MovieImagesResponse
import com.ameen.movies.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET(ApiEndPoints.TOP_MOVIES_ENDPOINT)
    suspend fun getTopRatedMoves(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int = 1
    ): Response<MoviesResponse>


    @GET(ApiEndPoints.MOVIE_GENRES_ENDPOINT)
    suspend fun getMovieGenres(
        @Query("api_key") apiKey: String
    ): Response<MovieGenresResponse>

    @GET(ApiEndPoints.MOVIE_IMAGES_ENDPOINT)
    suspend fun getMovieImages(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieImagesResponse>

    @GET(ApiEndPoints.MOVIE_SEARCH_ENDPOINT)
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") movieQuery: String,
        @Query("page") pageNumber: Int = 1
    ): Response<MoviesResponse>
}