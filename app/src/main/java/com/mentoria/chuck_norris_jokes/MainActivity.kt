package com.mentoria.chuck_norris_jokes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.mentoria.chuck_norris_jokes.model.Joke
import com.mentoria.chuck_norris_jokes.network.APIClient
import com.mentoria.chuck_norris_jokes.network.APIInterface
import com.mentoria.chuck_norris_jokes.viewModel.JokeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var jokeTextView: TextView
    private lateinit var jokeViewModel: JokeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jokeTextView = findViewById(R.id.joke)
        jokeViewModel = ViewModelProvider(this).get(JokeViewModel::class.java)

        jokeViewModel.getJoke()
        val refreshButton: Button = findViewById(R.id.newJoke)
        val sharebutton: ImageButton = findViewById(R.id.shareButton)

        refreshButton.setOnClickListener(){
            jokeViewModel.getJoke()
        }
        sharebutton.setOnClickListener(){
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            jokeTextView.text
            intent.putExtra("Share", jokeTextView.text)
            val chooser = Intent.createChooser(intent, "Share this Joke, Cowboy")
            startActivity(chooser)
        }

        jokeViewModel.jokeLiveData.observe(this, Observer { joke ->
           jokeTextView.text = joke.value
        })



    }


}