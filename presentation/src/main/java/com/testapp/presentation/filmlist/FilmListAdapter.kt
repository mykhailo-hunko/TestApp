package com.testapp.presentation.filmlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.testapp.domain.entity.Film
import com.testapp.presentation.DIFF_CALLBACK
import com.testapp.presentation.R
import com.testapp.presentation.databinding.ItemFilmBinding

class FilmListAdapter : ListAdapter<Film, FilmListAdapter.FilmViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(currentList.get(position))
    }

    override fun getItemCount(): Int = 10

    class FilmViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemFilmBinding.bind(view)

        fun bind(film: Film) {
            binding.title.text = film.originalTitle
            binding.description.text = film.overview
        }
    }


}