package com.mentoria.chuck_norris_jokes

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("/random")
    fun joke(): Call<Joke>
}