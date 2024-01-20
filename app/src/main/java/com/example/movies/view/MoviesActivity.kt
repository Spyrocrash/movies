package com.example.movies.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.data.Movies
import com.example.movies.model.apis.ApiInterface
import com.example.movies.view.adapter.CustomAdapter
import com.example.movies.viewmodel.MoviesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity(), CustomAdapter.ItemClickListener {

    lateinit var recyclerView: RecyclerView
    lateinit var moviesAdapter: CustomAdapter
    private var viewModel = MoviesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        initViews()
        initObservers()

        viewModel.getMovies()
    }

    private fun initObservers() {
        viewModel.
            movies.observe(this){
                moviesAdapter = CustomAdapter(it,this@MoviesActivity)
                recyclerView.adapter = moviesAdapter

        }
    }

    private fun initViews() {
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this@MoviesActivity,2)
    }

    override fun onItemClick(id: String) {
        val intent = Intent(this@MoviesActivity,MovieDetailsActivity::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
    }
}



