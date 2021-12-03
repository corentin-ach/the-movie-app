package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Movie
import com.google.gson.annotations.SerializedName
internal data class OneMovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val name: String,
    @SerializedName("vote_average")
    val vote: String,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("backdrop_path")
    val poster: String,
    @SerializedName("overview")
    val overview: String
//        @SerializedName("production_companies")
//        val production_companies: List<String> 
    //
)

internal fun OneMovieResponse.toMovie() = Movie(
    id = id,
    name = name,
    vote = vote,
    date = date,
    poster = "https://image.tmdb.org/t/p/w185$poster",
    overview = overview
)
