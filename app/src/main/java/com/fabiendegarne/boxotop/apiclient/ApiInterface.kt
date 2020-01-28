package com.fabiendegarne.boxotop.apiclient

import com.fabiendegarne.boxotop.models.Movie
import com.fabiendegarne.boxotop.models.MovieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/")
    fun getMovies(@Query("s")search: String?, @Query("type")type: String?, @Query("page")page: Int?): Call<Movie.MoviesSearchWrapper>

    @GET("/")
    fun getMovieDetails(@Query("i")id: String?): Call<MovieDetails>
}