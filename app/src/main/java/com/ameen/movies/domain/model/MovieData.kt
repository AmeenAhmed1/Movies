package com.ameen.movies.domain.model

import java.io.Serializable

data class MovieData(
    val genre_ids: List<Int>,
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
) : Serializable