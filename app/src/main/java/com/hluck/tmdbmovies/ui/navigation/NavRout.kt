package com.hluck.tmdbmovies.ui.navigation

sealed class NavRout(
    val rout:String
) {
    object Home: NavRout("home")
    object PopularMovieList: NavRout("popular_list")
    object UpcomingMovieList: NavRout("upcoming_list")
    object Details: NavRout("details")
}