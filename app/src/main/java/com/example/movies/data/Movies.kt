package com.example.movies.data

import com.example.movies.data.Search

data class Movies(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)