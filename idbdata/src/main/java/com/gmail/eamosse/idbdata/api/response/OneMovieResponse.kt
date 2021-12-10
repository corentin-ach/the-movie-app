package com.gmail.eamosse.idbdata.api.response

import android.util.Log
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.MovieDetails
import com.google.gson.annotations.SerializedName
internal data class OneMovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val name: String,
    @SerializedName("vote_average")
    val vote_average: Int,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("backdrop_path")
    val backdrop_path: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("vote_count")
    val vote_count: Int
)

internal fun OneMovieResponse.toMovieDetails() = MovieDetails(
    id = id,
    name = name,
    vote_average = vote_average,
    date = date,
    poster = "https://image.tmdb.org/t/p/w185$poster",
    overview = overview,
    backdrop_path = "https://image.tmdb.org/t/p/w185$backdrop_path" ,
    vote_count = vote_count,
)
