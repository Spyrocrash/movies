package com.example.movies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.movies.R
import com.example.movies.data.MovieDetails
import com.example.movies.model.apis.ApiInterface
import com.example.movies.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsActivity : AppCompatActivity() {

    lateinit var title: TextView
    lateinit var releaseDate: TextView
    lateinit var score: TextView
    lateinit var description: TextView
    lateinit var image: ImageView
    private val viewModel = MoviesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        initViews()
        val id:String? = intent.getStringExtra("id")
        viewModel.getMovieDetails(id)
        initObserver()

        /*//val id: String? = intent.getStringExtra("id")
        val retrofit = id?.let { ApiInterface.create().getMovieDetails(it,"996e60b1") }
        retrofit?.enqueue(object: Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                title.text = response.body()?.Title
                releaseDate.text = response.body()?.Released
                score.text = response.body()?.imdbRating
                description.text = response.body()?.Plot
                Picasso.get().load(response.body()?.Poster).into(image)

            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {

            }

        })*/

    }

    private fun initObserver() {
        viewModel.movieDetails.observe(this){
            title.text = it.Title
            releaseDate.text = it.Released
            score.text = it.imdbRating
            description.text = it.Plot
            Picasso.get().load(it.Poster).into(image)
        }
    }


    private fun initViews(){

        title = findViewById<TextView>(R.id.movie_details_title)
        releaseDate = findViewById<TextView>(R.id.movie_details_releaseDateValue)
        score = findViewById<TextView>(R.id.movie_details_scoreValue)
        description = findViewById<TextView>(R.id.movie_details_descriptionValue)
        image = findViewById<ImageView>(R.id.movie_details_image)

    }
}