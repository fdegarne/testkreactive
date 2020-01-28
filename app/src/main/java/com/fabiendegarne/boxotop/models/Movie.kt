package com.fabiendegarne.boxotop.models

import com.google.gson.annotations.SerializedName

class Movie {
    @SerializedName("Title")
    var title: String? = null
    @SerializedName("Year")
    var year: Int? = 0
    @SerializedName("Type")
    var type: String? = null
    var imdbID: String? = null
    @SerializedName("Poster")
    var image: String? = null

    override fun toString(): String {
        return "title: " + title + "\nyear: " + year + "\nID: " + imdbID
    }

    class MoviesSearchWrapper {
        @SerializedName("Search")
        var search: List<Movie> = emptyList()
        var totalResults: Int? = null
        @SerializedName("Response")
        var response: String? = null
        @SerializedName("Error")
        var error: String? = null
    }
}