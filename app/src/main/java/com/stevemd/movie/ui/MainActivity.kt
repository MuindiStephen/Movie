package com.stevemd.movie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stevemd.movie.R
import com.stevemd.movie.adapters.MovieAdapter
import com.stevemd.movie.data.remote.api.MovieRetrofitService
import com.stevemd.movie.data.remote.repository.MainRepository
import com.stevemd.movie.databinding.ActivityMainBinding
import com.stevemd.movie.viewmodel.MainViewModel
import com.stevemd.movie.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
     val movieAdapter by lazy { MovieAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val retrofitService = MovieRetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        binding.recyclerview.adapter = movieAdapter

        // One way data binding - Creating viewModel instance
        mainViewModel = ViewModelProvider(this,ViewModelFactory(mainRepository))[MainViewModel::class.java]

        mainViewModel.movieList.observe(this) { movies->
            movieAdapter.setMovies(movies)
        }
        mainViewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        mainViewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        mainViewModel.getAllMovies()
    }
}