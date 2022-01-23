package com.gmail.eamosse.imdb.ui.DiscoverTv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.data.Tv
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _token: MutableLiveData<Token> = MutableLiveData()
    val token: LiveData<Token>
        get() = _token

    private val _TvDiscover: MutableLiveData<List<Tv>> = MutableLiveData()
    val TvDiscover: LiveData<List<Tv>>
        get() = _TvDiscover

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

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

    fun getTv() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getTv()) {
                is Result.Succes -> {
                    _TvDiscover.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }
}
