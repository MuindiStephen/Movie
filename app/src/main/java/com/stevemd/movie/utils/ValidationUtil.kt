package com.stevemd.movie.utils

import com.stevemd.movie.model.Movie

object ValidationUtil {
    fun validateMovie(movie: Movie) : Boolean {
        if (movie.name.isNotEmpty() && movie.category.isNotEmpty()) {
            return true
        }
        return false
    }
}