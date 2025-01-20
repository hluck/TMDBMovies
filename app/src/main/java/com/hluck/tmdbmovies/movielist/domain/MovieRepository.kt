package com.hluck.tmdbmovies.movielist.domain

import com.hluck.tmdbmovies.app.common.utils.Resource
import com.hluck.tmdbmovies.movielist.data.local.MovieDao
import com.hluck.tmdbmovies.movielist.data.remote.MoviesApi
import com.hluck.tmdbmovies.movielist.domain.model.Movie
import com.hluck.tmdbmovies.movielist.mappers.toMovie
import com.hluck.tmdbmovies.movielist.mappers.toMovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val moviesApi: MoviesApi,
    private val movieDao: MovieDao
){

    fun getMovieList(
        forceFetchFromRemote:Boolean,
        category: String,
        page:Int
    ):Flow<Resource<List<Movie>>>{
        return flow {
            emit(Resource.Loading(true))
            val localMovies = movieDao.getMovieByCategory(category)
            val shouldLoadLocalMovies = localMovies.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovies){
                emit(Resource.Success(localMovies.map {
                    it.toMovie(category)
                }))
                emit(Resource.Loading(false))
                return@flow
            } else {
                val movies = try {
                    moviesApi.getMovies(category,page)
                } catch (e:Exception){
                    e.printStackTrace()
                    emit(Resource.Error("Error loading movies",null))
                    return@flow
                }
                val movieEntities = movies.results.map {
                    it.toMovieEntity(category)
                }
                movieDao.upsetMovies(movieEntities)
                emit(Resource.Success(movieEntities.map {
                    it.toMovie(category)
                }))
                emit(Resource.Loading(false))
            }
        }
    }

    fun getMovieById(id:Int):Flow<Resource<Movie>>{
        return flow {
            emit(Resource.Loading(true))

            val movie = movieDao.getMovieById(id)
            if (movie != null){
                emit(Resource.Success(movie.toMovie(movie.category)))
                emit(Resource.Loading(false))
                return@flow
            }
            emit(Resource.Loading(false))
            emit(Resource.Error("Movie not found",null))
        }
    }

}