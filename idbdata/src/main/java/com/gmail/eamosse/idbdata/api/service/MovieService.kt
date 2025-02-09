package com.gmail.eamosse.idbdata.api.service

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.MoviesResponse
import com.gmail.eamosse.idbdata.api.response.OneMovieResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieService {
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    @GET("genre/movie/list")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("/3/discover/movie")
    suspend fun getMovies(@Query("with_genres") catId: String): Response<MoviesResponse>

    // lien okkk https://api.themoviedb.org/3/movie/[ID_MOVIE]?api_key=507a86e6d98ae2b2cd600e594ee02637
    @GET("movie/{movie_id}")
    suspend fun getOneMovie(@Path("movie_id") movId: String): Response<OneMovieResponse>

    @GET("movie/top_rated")
    suspend fun getMovieTopRated(): Response<TopRatedResponse>
    @GET("movie/now_playing")
    suspend fun getTv(): Response<TvResponse>
}
