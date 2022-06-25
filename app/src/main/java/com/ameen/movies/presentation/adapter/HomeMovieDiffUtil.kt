package com.ameen.movies.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ameen.movies.domain.model.MovieData

object HomeMovieDiffUtil : DiffUtil.ItemCallback<MovieData>() {
    override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem == newItem
    }
}