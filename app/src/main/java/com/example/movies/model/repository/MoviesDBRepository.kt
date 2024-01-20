package com.example.movies.model.repository

import com.example.movies.data.MovieDetails
import com.example.movies.data.Movies
import retrofit2.Call

/**
*Repository provides information taken from movies API
* */
interface MoviesDBRepository {
    /**Return list of [Movies]*/
    fun getMovies(): Call<Movies>

    /**Return information for a single movie by returning [MovieDetails]
     * @param id - identification number of the needed movie
     * @param apiKey - key for an api call to make a request*/
    fun getMovieDetails(id:String, apiKey:String): Call<MovieDetails>
}