package com.example.movies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movies.Constants
import com.example.movies.data.MovieDetails
import com.example.movies.data.Movies
import com.example.movies.data.Search
import com.example.movies.model.repository.MoviesDBRepository
import com.example.movies.model.repository.MoviesDBRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesViewModel {


    private val _movies = MutableLiveData<List<Search>>()
    val movies: LiveData<List<Search>> = _movies

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    private val moviesRepository: MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovies() {
        val response = moviesRepository.getMovies()
        response.enqueue(object: Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                _movies.postValue(response.body()?.Search)
                val requestUrl = call.request().url()
                Log.d("messageRequest","Request URL: $requestUrl")
                Log.d("messageRequest","Request headers: ${call.request().headers()}")
                Log.d("messageRequest","Request method: ${call.request().method()}")
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("error",t.message.toString())
            }

        })
    }

    fun getMovieDetails(id: String?) {
        val response = id?.let { moviesRepository.getMovieDetails(it,Constants.API_KEY) }
        response?.enqueue(object: Callback<MovieDetails>{
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                _movieDetails.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}

