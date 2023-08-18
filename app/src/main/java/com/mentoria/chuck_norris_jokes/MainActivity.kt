package com.mentoria.chuck_norris_jokes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.mentoria.chuck_norris_jokes.model.Joke
import com.mentoria.chuck_norris_jokes.network.APIClient
import com.mentoria.chuck_norris_jokes.network.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    //perguntar o pq de não funcionar dessa forma
    //var jokeTextView: TextView = findViewById(R.id.joke)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getJoke()
        val refreshButton: Button = findViewById(R.id.newJoke)
        val sharebutton: ImageButton = findViewById(R.id.shareButton)
        val jokeTextView: TextView = findViewById(R.id.joke)

        refreshButton.setOnClickListener(){
            getJoke()
        }
        sharebutton.setOnClickListener(){
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            jokeTextView.text
            intent.putExtra("Share", jokeTextView.text)
            val chooser = Intent.createChooser(intent, "Share this Joke, Cowboy")
            startActivity(chooser)
        }

    }

    fun getJoke(){
        val retrofitAPIClient = APIClient.getRetrofitInstance("https://api.chucknorris.io/")
        val endpoint = retrofitAPIClient.create(APIInterface::class.java).getJoke()
        val jokeTextView: TextView = findViewById(R.id.joke)

        endpoint.enqueue(object : Callback<Joke> {
            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                if (response.isSuccessful){
                    val joke: Joke? = response.body()
                    if (joke != null){

                        jokeTextView.text = joke.value
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