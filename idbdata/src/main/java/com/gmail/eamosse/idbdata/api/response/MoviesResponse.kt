package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Movie
import com.google.gson.annotations.SerializedName

internal data class MoviesResponse(
    @SerializedName("movies")
    val genres: List<Movie>
) {
    data class Movie(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}

internal fun MoviesResponse.Movie.toMovie() = Movie(
    id = id,
    name = name
)

