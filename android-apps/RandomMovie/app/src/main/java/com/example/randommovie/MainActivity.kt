package com.example.randommovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.randommovie.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private lateinit var movieList: MutableList<String>
    private lateinit var binding: ActivityMainBinding
    private var isStarted: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateMovieList()

        binding.nextMovieButton.setOnClickListener {
            showRandomMovie()
        }
    }

    private fun showRandomMovie() {
        if (!isStarted) {
            selectButtonText()
            isStarted = true
        }

        if (movieList.isEmpty()) {
            updateMovieList()
        }
        val currentMovie = binding.movieTextView.text
        var randomMovie = movieList.removeAt(0)
        while (randomMovie == currentMovie)
            randomMovie = movieList.removeAt(0)
        binding.movieTextView.text = randomMovie
    }

    private fun updateMovieList() {
        movieList = resources.getStringArray(R.array.movies).toMutableList()
        movieList.shuffle()
    }

    private fun selectButtonText() {
        binding.nextMovieButton.text = resources.getString(R.string.nextMovie)
    }
}