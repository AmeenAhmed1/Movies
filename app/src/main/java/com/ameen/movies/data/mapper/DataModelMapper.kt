package com.ameen.movies.data.mapper

import com.ameen.movies.data.model.MovieGenresResponse
import com.ameen.movies.data.model.TopRatedMoviesResponse
import com.ameen.movies.domain.model.MovieData
import com.ameen.movies.domain.model.MovieGenre

class DataModelMapper {

    fun movieGenreResponseToViewState(movieGenresResponse: MovieGenresResponse): List<MovieGenre> {
        val movieGenreList = mutableListOf<MovieGenre>()
        for (genre in movieGenresResponse.genres) {
            movieGenreList.add(
                MovieGenre(
                    id = genre.id,
                    name = genre.name
                )
            )
        }
        return movieGenreList.toList()
    }


    fun movieDataResponseToViewState(movieDataList: TopRatedMoviesResponse): List<MovieData> {
        val movieList = mutableListOf<MovieData>()
        for (movie in movieDataList.results) {
            movieList.add(
                MovieData(
                    genre_ids = movie.genre_ids,
                    id = movie.id,
                    overview = movie.overview,
                    poster_path = movie.poster_path,
                    release_date = movie.release_date,
                    title = movie.title,
                    vote_average = movie.vote_average
                )
            )
        }
        return movieList.toList()
    }
}