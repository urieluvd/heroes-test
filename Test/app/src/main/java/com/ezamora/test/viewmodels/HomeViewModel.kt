package com.ezamora.test.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.ezamora.test.domain.CharacterResponse
import com.ezamora.test.repositories.HeroesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
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