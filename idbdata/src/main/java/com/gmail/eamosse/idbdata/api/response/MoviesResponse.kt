package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Movie
import com.google.gson.annotations.SerializedName
internal data class MoviesResponse(
    // changer movies par results
    @SerializedName("results")
    val results: List<Movie>,
) {
    data class Movie(
        @SerializedName("id")
        val id: Int,
        @SerializedName("original_title")
        val name: String,
        @SerializedName("vote_average")
        val vote: String,
        @SerializedName("release_date")
        val date: String,
        @SerializedName("poster_path")
        val poster: String
    )
}

internal fun MoviesResponse.Movie.toMovie() = Movie(
    id = id,
    name = name,
    vote = vote,
    date = date,
    poster = "https://image.tmdb.org/t/p/w185$poster",
    overview = "nothing"
)

