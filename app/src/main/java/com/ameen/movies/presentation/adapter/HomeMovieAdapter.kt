package com.ameen.movies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ameen.movies.core.util.IMAGE_BASE_URL
import com.ameen.movies.data.model.MovieModel
import com.ameen.movies.databinding.ItemMovieBinding
import com.ameen.movies.presentation.extention.loadImageFromUrl

class HomeMovieAdapter :
    PagingDataAdapter<MovieModel, HomeMovieAdapter.MyViewHolder>(HomeMovieDiffUtil) {

    inner class MyViewHolder(
        val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private var _binding: ItemMovieBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        _binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = getItem(position)

        holder.apply {
            currentItem?.let {
                binding.movieImage.loadImageFromUrl(IMAGE_BASE_URL + currentItem.poster_path)
                binding.movieTitle.text = currentItem.title
                binding.movieYear.text = currentItem.release_date
            }
        }
    }

    private var onItemClickListener: ((MovieModel) -> Unit)? = null
    fun onItemClicked(listener: (MovieModel) -> Unit) {
        onItemClickListener = listener
    }
}