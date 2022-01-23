package com.gmail.eamosse.idbdata.api.response

// import com.gmail.eamosse.idbdata.data.MovieLatest
import com.gmail.eamosse.idbdata.data.MovieTopRated
import com.google.gson.annotations.SerializedName

internal data class TopRatedResponse(

    @SerializedName("results")
    val results: List<MovieTopRated>,
) {
    data class MovieTopRated(
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

internal fun TopRatedResponse.MovieTopRated.toMovieTopRated() = MovieTopRated(
    id = id,
    name = name,
    date = date,
    poster = "https://image.tmdb.org/t/p/w185$poster"

)
