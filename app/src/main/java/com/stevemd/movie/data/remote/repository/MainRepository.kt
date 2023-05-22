package com.stevemd.movie.data.remote.repository

import com.stevemd.movie.data.remote.api.MovieRetrofitService
import com.stevemd.movie.model.Movie
import com.stevemd.movie.utils.NetworkState

class MainRepository constructor(
    private val retrofitService: MovieRetrofitService
){
    suspend fun getAllMovies():NetworkState<List<Movie>> {
       val response = retrofitService.getAllMovies()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }
}