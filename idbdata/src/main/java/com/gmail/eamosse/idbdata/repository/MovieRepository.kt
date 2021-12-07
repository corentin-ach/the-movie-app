package com.gmail.eamosse.idbdata.repository

import android.util.Log
import com.gmail.eamosse.idbdata.api.response.toCategory
import com.gmail.eamosse.idbdata.api.response.toEntity
import com.gmail.eamosse.idbdata.api.response.toMovie
import com.gmail.eamosse.idbdata.api.response.toToken
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.datasources.LocalDataSource
import com.gmail.eamosse.idbdata.datasources.OnlineDataSource
import com.gmail.eamosse.idbdata.utils.Result
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * La classe permettant de gérer les données de l'application
 */
class MovieRepository : KoinComponent {
    // Gestion des sources de données locales
    private val local: LocalDataSource by inject()

    // Gestion des sources de données en lignes
    private val online: OnlineDataSource by inject()

    /**
     * Récupérer le token permettant de consommer les ressources sur le serveur
     * Le résultat du datasource est converti en instance d'objets publiques
     */
    suspend fun getToken(): Result<Token> {
        return when (val result = online.getToken()) {
            is Result.Succes -> {
                // save the response in the local database
                local.saveToken(result.data.toEntity())
                // return the response
                Result.Succes(result.data.toToken())
            }
            is Result.Error -> result
        }
    }

    suspend fun getCategories(): Result<List<Category>> {
        return when (val result = online.getCategories()) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val categories = result.data.map {
                    it.toCategory()
                }
                Result.Succes(categories)
            }
            is Result.Error -> result
        }
    }

    suspend fun getMovies(catId: String): Result<List<Movie>> {
        return when (val result = online.getMovies(catId)) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les films de la réponse serveur
                // en liste de categories d'objets de l'applicatio
                val movies = result.data.map {
                    it.toMovie()
                }
                Log.d("CREATION", movies.toString())
                Result.Succes(movies)
            }
            is Result.Error -> result
        }
    }

    suspend fun getOneMovie(movId: String): Result<Movie> {
        return when (val result = online.getOneMovie(movId)) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les films de la réponse serveur
                // en liste de categories d'objets de l'applicatio
                val movie = result.data.toMovie()
                Log.d("CREATION ONE MOVIE", movie.toString())
                Result.Succes(movie)
            }
            is Result.Error -> result
        }
    }
}
