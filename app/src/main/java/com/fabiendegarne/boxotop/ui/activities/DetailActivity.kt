package com.fabiendegarne.boxotop.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fabiendegarne.boxotop.R
import com.fabiendegarne.boxotop.databinding.MovieDetailBinding
import com.fabiendegarne.boxotop.ui.viewmodels.MovieDetailViewModel

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val movieId: String = intent.getStringExtra("movie_id")

        val movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)

        movieDetailViewModel.getMovieDetails(movieId).observe(this, Observer { movieDetailViewModel ->
            DataBindingUtil.setContentView<MovieDetailBinding>(this, R.layout.activity_detail)
                .apply {
                    this.setLifecycleOwner(this@DetailActivity)
                    this.movieDetail = movieDetailViewModel
                }
        })
    }
}
