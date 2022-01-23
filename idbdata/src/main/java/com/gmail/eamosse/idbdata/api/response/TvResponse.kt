package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.Tv
import com.google.gson.annotations.SerializedName

internal data class TvResponse(
    @SerializedName("results")
    val results: List<Tv>,
) {
    data class Tv(
        @SerializedName("id")
        val id: Int,
        @SerializedName("original_title")
        val name: String,
        @SerializedName("vote_average")
        val vote: String,
        @SerializedName("release_date")
        val date: String,
        @SerializedName("poster_path")
        val poster: String?
    )
}

internal fun TvResponse.Tv.toTv() = Tv(
    id = id,
    name = name,
    vote = vote,
    date = date,
    poster = "https://image.tmdb.org/t/p/w500$poster"
)
