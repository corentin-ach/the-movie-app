package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.MovieLatest
import com.google.gson.annotations.SerializedName

internal data class MovieLatestResponse(

    @SerializedName("results")
    val results: List<MovieLatest>,
) {
    data class MovieLatest(
        @SerializedName("id")
        val id: Int,
        @SerializedName("original_title")
        val name: String,
        @SerializedName("release_date")
        val date: String,
        @SerializedName("poster_path")
        val poster: String?

    )
}

internal fun MovieLatestResponse.MovieLatest.toMovieLatest() = MovieLatest(
    id = id,
    name = name,
    date = date,
    poster = "https://image.tmdb.org/t/p/w185$poster"

)
