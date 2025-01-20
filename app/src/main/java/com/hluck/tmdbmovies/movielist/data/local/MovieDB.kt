package com.hluck.tmdbmovies.movielist.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hluck.tmdbmovies.movielist.data.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDB:RoomDatabase() {

    abstract fun movieDao():MovieDao


    companion object{

        @Volatile
        private var INSTANCE:MovieDB? = null

        fun getDatabase(context: android.content.Context):MovieDB{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDB::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}