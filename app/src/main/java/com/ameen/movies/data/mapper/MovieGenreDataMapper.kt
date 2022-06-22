package com.ameen.movies.data.mapper

import com.ameen.movies.data.model.MovieGenresResponse
import com.ameen.movies.domain.model.MovieGenre

class MovieGenreDataMapper {

    fun movieGenreResponseToViewState(movieGenresResponse: MovieGenresResponse): MutableList<MovieGenre> {
        val movieGenreList = mutableListOf<MovieGenre>()
        for (genre in movieGenresResponse.genres) {
            movieGenreList.add(
                MovieGenre(
                    id = genre.id,
                    name = genre.name
                )
            )
        }
        return movieGenreList
    }

}