package com.hluck.tmdbmovies.movielist.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Movie(
    val id: Int,
    val category:String,
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)