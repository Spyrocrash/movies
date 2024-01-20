package com.example.movies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
}

