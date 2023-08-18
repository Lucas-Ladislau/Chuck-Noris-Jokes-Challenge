package com.mentoria.chuck_norris_jokes.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentoria.chuck_norris_jokes.model.Joke
import com.mentoria.chuck_norris_jokes.network.APIClient
import com.mentoria.chuck_norris_jokes.network.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeViewModel : ViewModel() {
    var jokeLiveData = MutableLiveData<Joke>()
    var categories = MutableLiveData<List<String>>()
    val retrofitAPIClient = APIClient.getRetrofitInstance("https://api.chucknorris.io/")
    var endpointInterface = retrofitAPIClient.create(APIInterface::class.java)

    fun getJoke(){
        endpointInterface.getJoke().enqueue(object : Callback<Joke> {
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

    fun getCategories(){
        endpointInterface.getCategories().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        categories.value = response.body()
                        Log.v("categorias", categories.value.toString())
                    }
                }else{
                    println("Resposta não foi bem sucedida: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                println("Deu erro: ${t.message}")
            }

        })

    }
}