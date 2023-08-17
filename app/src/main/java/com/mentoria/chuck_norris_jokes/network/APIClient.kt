package com.mentoria.chuck_norris_jokes.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    //cria um método estático
    companion object{
        fun getRetrofitInstance(path: String) : Retrofit{
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }

}