package com.example.movies.model.repository

import com.example.movies.Constants.Companion.API_KEY
import com.example.movies.Constants.Companion.MOVIE_NAME
import com.example.movies.data.MovieDetails
import com.example.movies.data.Movies
import com.example.movies.model.apis.ApiInterface
import retrofit2.Call

class MoviesDBRepositoryImpl : MoviesDBRepository {

    private val apiInterface = ApiInterface.create()


    override fun getMovies(): Call<Movies> {
        return apiInterface.getMovies(API_KEY, MOVIE_NAME)
    }

    override fun getMovieDetails(id:String, apiKey:String): Call<MovieDetails> {
        return apiInterface.getMovieDetails(id, apiKey)
    }

}



