package com.example.randommovie

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.randommovie.databinding.ActivityMainBinding
import com.example.randommovie.data.repository.MovieRepository
import com.example.randommovie.data.dto.Movie

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isStarted: Boolean = false
    private lateinit var repository: MovieRepository
    private lateinit var movies: MutableList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = MovieRepository(resources)

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

        if (movies.isEmpty()) {
            updateMovieList()
        }
        val currentMovie = binding.movieTitle.text
        var randomMovie = movies.removeAt(0)
        while (randomMovie.title == currentMovie)
            randomMovie = movies.removeAt(0)

        showMovie(randomMovie)
    }

    private fun updateMovieList() {
        val rawMovies = repository.getAllMovies()
        if (rawMovies.isEmpty()) {
            Log.d("MainActivity", "Fail to load movies from JSON")
        } else {
            movies = rawMovies.toMutableList()
            movies.shuffle()
        }

    }

    private fun selectButtonText() {
        binding.nextMovieButton.text = resources.getString(R.string.nextMovie)
    }

    private fun showMovie(movie: Movie) {
        val hours = movie.duration / 60
        val minutes = movie.duration % 60

        val duration: String = (hours / 10).toString() + (hours % 10).toString() + ":" + (minutes / 10).toString() + (minutes % 10).toString()

        binding.movieTitle.text = movie.title
        binding.movieDuration.text = duration
        binding.movieRating.text = movie.rating.toString()
        binding.movieYear.text = movie.year.toString()
        var actors: String = ""
        for (i in 0 until movie.actors.size - 1) {
            actors += movie.actors[i] + "\n"
        }
        actors += movie.actors[movie.actors.size - 1]
        binding.movieActors.text = actors

        var genres: String = ""
        for (i in 0 until movie.genres.size - 1) {
            genres += movie.genres[i] + "\n"
        }
        genres += movie.genres[movie.genres.size - 1]
        binding.movieGenres.text = genres
    }
}