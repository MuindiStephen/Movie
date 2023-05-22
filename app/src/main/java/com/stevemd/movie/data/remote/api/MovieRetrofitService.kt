package com.stevemd.movie.data.remote.api

import com.stevemd.movie.model.Movie
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MovieRetrofitService {
    @GET("movies.json")
    suspend fun getAllMovies() : Response<List<Movie>>


    companion object {
        var movieRetrofitService:MovieRetrofitService? = null

        fun getInstance() : MovieRetrofitService {

            if (movieRetrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
               movieRetrofitService = retrofit.create(MovieRetrofitService::class.java)
            }
            return movieRetrofitService!!
        }
    }
}