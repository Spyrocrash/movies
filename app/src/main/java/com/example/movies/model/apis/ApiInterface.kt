package com.example.movies.model.apis

import com.example.movies.data.MovieDetails
import com.example.movies.data.Movies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/")
    fun getMovies(@Query("apikey") apiKey: String,
                  @Query("s") movieName: String,
    ): Call<Movies>

    @GET("/")
    fun getMovieDetails(@Query("i") imdbId: String,
                  @Query("apikey") apiKey: String,
    ): Call<MovieDetails>

        companion object {
            var BASE_URL = "https://www.omdbapi.com/"
            fun create() : ApiInterface {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                return retrofit.create(ApiInterface::class.java)
            }
        }
    }


