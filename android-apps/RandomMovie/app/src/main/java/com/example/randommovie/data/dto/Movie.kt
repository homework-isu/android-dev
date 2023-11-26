package com.example.randommovie.data.dto

class Movie (
    val title: String,
    val duration: Int,
    val year: Int,
    val genres: List<String>,
    val actors: List<String>,
    val rating: Double,
)