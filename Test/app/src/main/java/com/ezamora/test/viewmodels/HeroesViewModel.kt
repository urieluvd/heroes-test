package com.ezamora.test.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ezamora.test.domain.CharacterResponse
import com.ezamora.test.repositories.HeroesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val repository: HeroesRepository,
    application: Application
) : AndroidViewModel(application) {
    private val _heroesData = MutableLiveData<CharacterResponse?>()
    val heroesData : LiveData<CharacterResponse?> get() = _heroesData

    fun getCharacters(offset: Int){
        viewModelScope.launch {
            val request = repository.getCharacters(offset)
            if (request != null){
                _heroesData.value = request
            }
        }
    }
}