package com.gmail.eamosse.idbdata.data

data class MovieDetails(
    val id: Int,
    val name: String,
    val vote_average: String,
    val date: String,
    val poster: String,
    val backdrop_path: String,
    val overview: String,
    val vote_count: String
    // var production_companies: Array<Company>
)
