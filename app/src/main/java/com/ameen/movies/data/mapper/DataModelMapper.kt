package com.ameen.movies.data.mapper

import com.ameen.movies.data.model.MovieGenresResponse
import com.ameen.movies.data.model.MovieImagesResponse
import com.ameen.movies.data.model.MoviesResponse
import com.ameen.movies.domain.model.MovieData
import com.ameen.movies.domain.model.MovieGenre
import com.ameen.movies.domain.model.MovieImages

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


    fun movieDataResponseToViewState(movieDataList: MoviesResponse): List<MovieData> {
        val movieList = mutableListOf<MovieData>()
        for (movie in movieDataList.results) {
            movieList.add(
                MovieData(
                    genre_ids = movie.genre_ids,
                    id = movie.id,
                    overview = movie.overview,
                    poster_path = movie.poster_path ?: "",
                    backdrop_path = movie.backdrop_path ?: "",
                    release_date = movie.release_date,
                    title = movie.title,
                    vote_average = movie.vote_average
                )
            )
        }
        return movieList.toList()
    }


    fun movieImagesResponseToViewState(movieImagesResponse: MovieImagesResponse): MovieImages {
        val imageLinks = mutableListOf<String>()
        for (image in movieImagesResponse.backdrops) {
            imageLinks.add(image.file_path)
        }
        return MovieImages(imageLinks)
    }
}