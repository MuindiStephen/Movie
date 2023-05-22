package com.stevemd.movie.data.remote.api

import com.stevemd.movie.model.Movie

interface MovieRetrofitService {

    suspend fun getAllMovies() : List<Movie>
}