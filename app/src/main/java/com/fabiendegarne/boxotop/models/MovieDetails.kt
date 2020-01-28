package com.fabiendegarne.boxotop.models

import com.google.gson.annotations.SerializedName

class MovieDetails {
    @SerializedName("Title")
    var title:    String?  = null
    @SerializedName("Released")
    var release:  String?  = null
    @SerializedName("Runtime")
    var runtime:  String?  = null
    @SerializedName("Director")
    var director: String?  = null
    @SerializedName("Plot")
    var synopsis: String?  = null
    @SerializedName("imdbRating")
    var rating: Float?     = null
    @SerializedName("Language")
    var language: String?  = null
    @SerializedName("Writer")
    var writers:  String?  = null
    @SerializedName("Actors")
    var casting:  String?  = null
    @SerializedName("Poster")
    var image:    String?  = null
}