package com.mentoria.chuck_norris_jokes

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.mentoria.chuck_norris_jokes.model.Joke
import com.mentoria.chuck_norris_jokes.network.APIClient
import com.mentoria.chuck_norris_jokes.network.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getJoke()


    }

    fun getJoke(){
        val retrofitAPIClient = APIClient.getRetrofitInstance("https://api.chucknorris.io/")
        val endpoint = retrofitAPIClient.create(APIInterface::class.java).getJoke()


        endpoint.enqueue(object : Callback<Joke> {
            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                if (response.isSuccessful){
                    val joke: Joke? = response.body()
                    if (joke != null){
                        val value = joke.value
                        val jokeTextView: TextView = findViewById(R.id.joke) // ou use o Data Binding se estiver configurado
                        jokeTextView.text = value
                    }
                }else{
                    println("Resposta não bem sucedida: ${response.code()}")
                }

            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {
                println("Deu erro: ${t.message}")
            }

        })
    }
}