package com.ameen.movies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.ameen.movies.core.util.IMAGE_BASE_URL
import com.ameen.movies.databinding.ItemMovieBinding
import com.ameen.movies.domain.model.MovieData
import com.ameen.movies.presentation.extention.loadImageFromUrl

class HomeMovieAdapter :
    RecyclerView.Adapter<HomeMovieAdapter.MyViewHolder>() {

    inner class MyViewHolder(
        val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private var _binding: ItemMovieBinding? = null

    val diffUtil = AsyncListDiffer(this, HomeMovieDiffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        _binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = diffUtil.currentList[position]

        holder.apply {
            currentItem?.let {
                binding.movieImage.loadImageFromUrl(
                    IMAGE_BASE_URL + currentItem.poster_path,
                    binding.loadingProgress
                )
                binding.movieTitle.text = currentItem.title
                binding.movieYear.text = currentItem.release_date
            }

            binding.itemMovie.setOnClickListener {
                onItemClickListener?.let { it(currentItem) }
            }
        }
    }

    private var onItemClickListener: ((MovieData) -> Unit)? = null
    fun onItemClicked(listener: (MovieData) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = diffUtil.currentList.size
}