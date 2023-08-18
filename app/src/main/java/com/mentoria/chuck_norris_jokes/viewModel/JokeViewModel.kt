package com.mentoria.chuck_norris_jokes.viewModel

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentoria.chuck_norris_jokes.R
import com.mentoria.chuck_norris_jokes.model.Joke
import com.mentoria.chuck_norris_jokes.network.APIClient
import com.mentoria.chuck_norris_jokes.network.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeViewModel : ViewModel() {
    var jokeLiveData = MutableLiveData<Joke>()

    fun getJoke(){
        val retrofitAPIClient = APIClient.getRetrofitInstance("https://api.chucknorris.io/")
        val endpoint = retrofitAPIClient.create(APIInterface::class.java).getJoke()

        endpoint.enqueue(object : Callback<Joke> {
            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                if (response.isSuccessful){
                    val joke: Joke? = response.body()
                    if (joke != null){
                        jokeLiveData.value = joke
                    }
                }else{
                    println("Resposta não foi bem sucedida: ${response.code()}")
                }

            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {
                println("Deu erro: ${t.message}")
            }

        })
    }
}