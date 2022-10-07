package com.ezamora.test.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ezamora.test.repositories.HeroesRepository
import com.ezamora.test.views.models.HeroesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val repository: HeroesRepository,
    application: Application
) : AndroidViewModel(application) {
    private val _heroesData = MutableLiveData<HeroesModel>()
    val heroesData : LiveData<HeroesModel> get() = _heroesData

    fun getCharacteres(
        offset: Int
    ){
        viewModelScope.launch {
            val request = repository.getCharacteres(offset)
            if (request != null){
                _heroesData.value = request
            }
        }
    }
}