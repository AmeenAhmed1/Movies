package com.ameen.movies.core.util

const val PAGE_SIZE = 20
const val INITIAL_LOADING_PAGE_NUMBER = 1
const val BASE_URL = "https://api.themoviedb.org/3/"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original/"
const val API_KEY = "cf2a0e44ebd0f153e44c7a54007b3f1c"

const val RECYCLER_VIEW_GRID_SPAN_SIZE = 2
const val API_USER_AGENT = 1


//EndPoints Reference.
object ApiEndPoints {
    const val TOP_MOVIES_ENDPOINT = "movie/top_rated"
    const val MOVIE_GENRES_ENDPOINT = "genre/movie/list"
    const val MOVIE_IMAGES_ENDPOINT = "movie/{movie_id}/images"
    const val MOVIE_SEARCH_ENDPOINT = "search/movie"
}