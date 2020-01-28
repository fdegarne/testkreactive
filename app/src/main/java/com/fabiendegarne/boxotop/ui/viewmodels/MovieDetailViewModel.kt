package com.fabiendegarne.boxotop.ui.viewmodels

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.fabiendegarne.boxotop.apiclient.ApiClient
import com.fabiendegarne.boxotop.apiclient.ApiInterface
import com.fabiendegarne.boxotop.models.MovieDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel: ViewModel {
    var title: String? = null
    var release: String? = null
    var runtime: String? = null
    var director: String? = null
    var synopsis: String? = null
    var rating: Float? = null
    var language: String? = null
    var writers: String? = null
    var casting: String? = null
    var image: String? = ""

    constructor() : super()
    constructor(movieDetail: MovieDetails) : super() {
        this.title = movieDetail.title
        this.release = movieDetail.release
        this.runtime = movieDetail.runtime
        this.director = movieDetail.director
        this.synopsis = movieDetail.synopsis
        this.rating = movieDetail.rating!! / 2
        this.language = movieDetail.language
        this.writers = movieDetail.writers
        this.casting = movieDetail.casting
        this.image = movieDetail.image
    }

    var movieDetailMutableLiveData = MutableLiveData<MovieDetailViewModel>()

    fun getMovieDetails(movieId: String): MutableLiveData<MovieDetailViewModel> {
        val moviesService = ApiClient.getClient().create(ApiInterface::class.java)
        val details: Call<MovieDetails> = moviesService.getMovieDetails(movieId)

        details.enqueue(object : Callback<MovieDetails> {

            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                val mDetail: MovieDetails? = response.body()
                println("mDetail: " + mDetail!!.image.toString())
                movieDetailMutableLiveData.value = MovieDetailViewModel(mDetail!!)
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        return movieDetailMutableLiveData
    }

    companion object {
        @BindingAdapter("preview")
        @JvmStatic
        fun loadImage(imageView: ImageView, imageUrl: String?) {
            if (!imageUrl.isNullOrBlank()) {
                Glide.with(imageView.context)
                    .load(imageUrl)
                    .centerInside()
                    .into(imageView)
            }
        }
    }
}