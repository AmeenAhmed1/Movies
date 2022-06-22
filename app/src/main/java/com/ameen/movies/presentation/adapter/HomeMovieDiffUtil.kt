package com.ameen.movies.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ameen.movies.data.model.MovieModel

object HomeMovieDiffUtil : DiffUtil.ItemCallback<MovieModel>() {
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem == newItem
    }
}