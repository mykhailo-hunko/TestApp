package com.testapp.presentation.filmlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.testapp.domain.entity.Film
import com.testapp.presentation.DIFF_CALLBACK
import com.testapp.presentation.databinding.ItemFilmBinding

class FilmListAdapter : ListAdapter<Film, FilmListAdapter.FilmViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

    class FilmViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            binding.title.text = film.title
            binding.description.text = film.overview
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/original" + film.posterPath)
                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
                .circleCrop()
                .into(binding.image)
        }
    }


}