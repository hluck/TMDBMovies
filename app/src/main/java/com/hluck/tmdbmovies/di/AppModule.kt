package com.hluck.tmdbmovies.di

import android.content.Context
import com.hluck.tmdbmovies.movielist.data.local.MovieDB
import com.hluck.tmdbmovies.movielist.data.local.MovieDao
import com.hluck.tmdbmovies.movielist.data.remote.AuthInterceptor
import com.hluck.tmdbmovies.movielist.data.remote.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val authInterceptor: AuthInterceptor = AuthInterceptor()

    private val authClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): MovieDB {
        return MovieDB.getDatabase(appContext)
    }

    @Provides
    fun provideNetworkCacheDao(appDatabase: MovieDB): MovieDao {
        return appDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun providerOutDeviceApi(): MoviesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MoviesApi.BASE_URL)
            .client(authClient)
            .build()
            .create(MoviesApi::class.java)
    }

}