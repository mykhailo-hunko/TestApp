package com.testapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.testapp.domain.entity.Film

val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Film>() {

    override fun areItemsTheSame(oldUser: Film, newUser: Film) = oldUser.id == newUser.id

    override fun areContentsTheSame(oldUser: Film, newUser: Film) = oldUser == newUser
}