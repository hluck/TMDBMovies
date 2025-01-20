package com.hluck.tmdbmovies.movielist.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.hluck.tmdbmovies.movielist.data.local.entity.MovieEntity
import com.hluck.tmdbmovies.movielist.domain.model.Movie

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsetMovies(movies: List<MovieEntity>)

    @androidx.room.Query("SELECT * FROM MovieEntity WHERE id = :id")
    suspend fun getMovieById(id:Int):MovieEntity

    @Query("SELECT * FROM movieentity WHERE category = :category")
    suspend fun getMovieByCategory(category:String):List<MovieEntity>
}