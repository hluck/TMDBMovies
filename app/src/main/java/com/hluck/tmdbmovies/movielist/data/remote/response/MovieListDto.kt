package com.hluck.tmdbmovies.movielist.data.remote.response

data class MovieListDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)