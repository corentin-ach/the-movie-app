package com.gmail.eamosse.imdb.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.MovieDetails
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

/**
 * VM permettant de gérer les données de la vue
 */
class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _token: MutableLiveData<Token> = MutableLiveData()
    val token: LiveData<Token>
        get() = _token

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>>
        get() = _categories

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>>
        get() = _movies

    // pour lire one movie
    private val _onemovie: MutableLiveData<MovieDetails> = MutableLiveData()
    val movie: LiveData<MovieDetails>
        get() = _onemovie

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    /**
     * Block d'initialisation du viewmodel
     * On en profite (pour l'instant) pour récupérer le token utilisateur
     */
    init {
        /**
         * getToken est une méthode suspendue, par conséquent elle doit être appelée dans une coroutine
         * De plus, le repository accède à une source de données (API ou BDD); il faut appeler la méthode
         * dans un thread secondaire
         *
         * On utilise l'attribut viewModelScope qui est une coroutine lié au cycle de vie du VM
         * Puis on exécute la méthode getToken dans un [Dispatchers.IO], soit dans un thread secondaire
         */
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getToken()) {
                is Result.Succes -> {
                    _token.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getCategories()) {
                is Result.Succes -> {
                    _categories.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getMovies(catId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getMovies(catId)) {
                is Result.Succes -> {
                    Log.d("CREATION", result.toString())
                    _movies.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    // recuperer un seul film pour avoir toutes ses infos
    fun getOneMovie(movId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getOneMovie(movId)) {
                is Result.Succes -> {
                    Log.d("CREATION ONE MOVIE", result.toString())
                    _onemovie.postValue(result.data)
                }
                is Result.Error -> {
                    Log.d("***Result get One Movie - View Model", "Error")
                    _error.postValue(result.message)
                }
            }
        }
    }
}
