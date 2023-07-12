package com.example.moviesappv2.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesappv2.R
import com.example.moviesappv2.databinding.RvCardMoviePosterBinding
import com.example.moviesappv2.domain.model.Movie

/**
 * Created by Omar Elashry.
 */
class PosterMoviesAdapter(private val itemListener: PosterItemListener) :
    ListAdapter<Movie, PosterMoviesAdapter.MyViewHolder>(PosterItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding =
            RvCardMoviePosterBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MyViewHolder(itemView: View, binding: RvCardMoviePosterBinding) :
        RecyclerView.ViewHolder(itemView) {
        private var binding: RvCardMoviePosterBinding

        init {
            this.binding = binding
            itemView.setOnClickListener {
                itemListener.onClick(
                    getItem(adapterPosition),
                    adapterPosition
                )
            }
        }

        fun bind(item: Movie) {
            Glide.with(binding.root.context).load(item.posterPath)
                .placeholder(R.drawable.poster_placeholder)
                .error(R.drawable.poster_placeholder)
                .into(binding.posterImgView)
        }
    }
}


interface PosterItemListener {
    fun onClick(item: Movie, position: Int)
}

private class PosterItemDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem

}