package com.fabiendegarne.boxotop.ui.viewmodels

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.fabiendegarne.boxotop.apiclient.ApiClient
import com.fabiendegarne.boxotop.apiclient.ApiInterface
import com.fabiendegarne.boxotop.config.MediaType
import com.fabiendegarne.boxotop.models.Movie
import com.fabiendegarne.boxotop.models.Movie.MoviesSearchWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class MovieViewModel: ViewModel, Serializable {
    var title: String? = null
    var year: Int? = null
    var type: String? = null
    var imdbID: String? = null
    var image: String? = null

    constructor() : super()
    constructor(movie: Movie) : super() {
        this.title = movie.title
        this.year = movie.year
        this.type = movie.type
        this.imdbID = movie.imdbID
        this.image = movie.image
    }

    var arrayMovieMutableLiveData = MutableLiveData<ArrayList<MovieViewModel>>()
    var arrayMovieViewModel = ArrayList<MovieViewModel>()

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(imageView: ImageView, imageUrl: String) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .centerInside()
                .into(imageView)
        }
    }



    fun getArrayMovies(searchText: String, page: Int): MutableLiveData<ArrayList<MovieViewModel>>
    {
        val moviesService = ApiClient.getClient().create(ApiInterface::class.java)
        val movies: Call<MoviesSearchWrapper> = moviesService.getMovies(searchText, MediaType.MOVIE.type, page)

        movies.enqueue(object: Callback<MoviesSearchWrapper> {

            override fun onResponse(call: Call<MoviesSearchWrapper>, response: Response<MoviesSearchWrapper>) {
                val movieWrapper: MoviesSearchWrapper? = response.body()

                if (movieWrapper!!.search != null) {
                    for (movie in movieWrapper!!.search){
                        arrayMovieViewModel!!.add(MovieViewModel(movie))
                    }
                    arrayMovieMutableLiveData.value = arrayMovieViewModel
                }
            }

            override fun onFailure(call: Call<MoviesSearchWrapper>, t: Throwable) {

            }

        })
        return arrayMovieMutableLiveData
    }
}