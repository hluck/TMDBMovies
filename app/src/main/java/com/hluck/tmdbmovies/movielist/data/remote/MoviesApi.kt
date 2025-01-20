package com.hluck.tmdbmovies.movielist.data.remote

import com.hluck.tmdbmovies.movielist.data.remote.response.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "b9e509634916bf34f19dbab619dc2862"
        const val TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiOWU1MDk2MzQ5MTZiZjM0ZjE5ZGJhYjYxOWRjMjg2MiIsIm5iZiI6MTcyODAwNTMwNS41MTQsInN1YiI6IjY2ZmY0NGI5NmZjNzRlNTc1NmY3ZWYyNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ._Ho_LZbSVFB5EQPLGh_ec0EUTUCm8FKUAmXYrFRdByE"
    }

    @GET("movie/{category}")
    suspend fun getMovies(
        @Path("category") category:String,
        @Query("page") page :Int
    ):MovieListDto

}