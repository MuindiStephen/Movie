package com.stevemd.movie.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stevemd.movie.databinding.AdapterMovieBinding
import com.stevemd.movie.model.Movie
import com.stevemd.movie.utils.ValidationUtil

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MainViewHolder>() {

    class MainViewHolder (val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movieList[position]
        if (ValidationUtil.validateMovie(movie)) {
            holder.binding.name.text = movie.name
            Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }


    var movieList = mutableListOf<Movie>()

    @SuppressLint("NotifyDataSetChanged")
    fun setMovies(movies: List<Movie>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }
}