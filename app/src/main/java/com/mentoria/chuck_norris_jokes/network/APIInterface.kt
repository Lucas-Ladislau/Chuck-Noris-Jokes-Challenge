package com.mentoria.chuck_norris_jokes.network

import com.mentoria.chuck_norris_jokes.model.Joke
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("jokes/random")
    fun getJoke(): Call<Joke>

    @GET("jokes/categories")
    fun getCategories(): Call<List<String>>


}