package com.example.randommovie.data.repository

import android.content.res.Resources
import android.util.Log
import com.example.randommovie.R
import com.google.gson.Gson
import com.example.randommovie.data.dto.Movie
import com.google.gson.reflect.TypeToken
import java.io.IOException

class MovieRepository(private val resources: Resources) {

    fun getAllMovies(): List<Movie> {
        return try {
            val inputStream = resources.openRawResource(R.raw.movies)
            val jsonString = inputStream.bufferedReader().use { it.readText() }

            val gson = Gson()
            val movieListType = object : TypeToken<List<Movie>>() {}.type
            gson.fromJson(jsonString, movieListType)
        } catch (e: IOException) {
            Log.e("MainActivity", "Error loading JSON from raw resources", e)
            emptyList()
        }
    }

}
