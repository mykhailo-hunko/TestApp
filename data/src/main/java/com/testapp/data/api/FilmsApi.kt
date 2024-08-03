package com.testapp.data.api

import com.testapp.data.FilmResponse
import retrofit2.http.GET

interface FilmsApi {

    @GET("discover/movie?page=1")

    suspend fun getFilms(): FilmResponse
}