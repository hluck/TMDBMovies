package com.hluck.tmdbmovies.movielist.mappers

import com.hluck.tmdbmovies.movielist.data.local.entity.MovieEntity
import com.hluck.tmdbmovies.movielist.data.remote.response.MovieDto
import com.hluck.tmdbmovies.movielist.domain.model.Movie

fun MovieEntity.toMovie(
    category: String
): Movie {
    return Movie(
        id = id,
        category = category,
        adult = adult,
        backdrop_path = backdrop_path,
        genre_ids = try {
            genre_ids.split(",").map { it.toInt() }
        } catch (e: Exception) {
            emptyList<Int>()
        },
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        video = video,
        vote_average = vote_average,
        vote_count = vote_count
    )
}


fun MovieDto.toMovieEntity(category:String): MovieEntity {
    return MovieEntity(
        id = id ?: -1,
        category = category,
        adult = adult?:false,
        backdrop_path = backdrop_path ?: "",
        genre_ids = try {
            genre_ids?.joinToString(",") ?: ""
        } catch (e:Exception){
            ""
        },
        original_language = original_language ?: "",
        original_title = original_title ?: "",
        overview = overview ?: "",
        popularity = popularity ?: -1.0,
        poster_path = poster_path ?: "",
        release_date = release_date ?: "",
        title = title ?: "",
        video = video ?: false,
        vote_average = vote_average ?: -1.0,
        vote_count = vote_count ?: -1,
    )
}