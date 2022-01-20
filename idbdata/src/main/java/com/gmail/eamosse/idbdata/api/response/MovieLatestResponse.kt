package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.MovieLatest
import com.google.gson.annotations.SerializedName

internal data class MovieLatestResponse(

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

internal fun MovieLatestResponse.toMovieLatest() = MovieLatest(
    id = id,
    name = name,
    vote = vote,
    date = date,
    poster = poster

)
