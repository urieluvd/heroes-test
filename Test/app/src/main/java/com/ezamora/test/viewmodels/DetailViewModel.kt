package com.ezamora.test.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.ezamora.test.domain.CharacterResponse
import com.ezamora.test.domain.Comic
import com.ezamora.test.domain.Series
import com.ezamora.test.repositories.HeroesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: HeroesRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _character : MutableLiveData<com.ezamora.test.domain.Character> by lazy {
        MutableLiveData<com.ezamora.test.domain.Character>()
    }
    val character: LiveData<com.ezamora.test.domain.Character> = _character

    private val _comics : MutableLiveData<ArrayList<Comic>> by lazy {
        MutableLiveData<ArrayList<Comic>>(arrayListOf())
    }
    val comics: LiveData<ArrayList<Comic>> = _comics

    private val _series : MutableLiveData<ArrayList<Series>> by lazy {
        MutableLiveData<ArrayList<Series>>(arrayListOf())
    }
    val series: LiveData<ArrayList<Series>> = _series

    private val _heroesData = MutableLiveData<CharacterResponse?>()
    val heroesData : LiveData<CharacterResponse?> get() = _heroesData

    private var characterId: Int = 0

    fun setCharacterId(id: Int){
        characterId = id
    }

    fun getCharacter(){
        viewModelScope.launch {
            val result = repository.getCharacter(characterId)

            result?.let {

                Timber.i("CharacterDetails: $it")

                _character.value = it.data.results[0]

            }
        }
    }

    fun getComics(){
        viewModelScope.launch {
            val result = repository.getCharacterComics(characterId, 0)

            result?.let {

                Timber.i("CharacterDetails: $it")

                _comics.value?.addAll(it.data.results)
                _comics.value = _comics.value

            }
        }
    }

    fun getSeries(){
        viewModelScope.launch {
            val result = repository.getCharacterSeries(characterId, 0)

            result?.let {

                Timber.i("CharacterDetails: $it")

                _series.value?.addAll(it.data.results)
                _series.value = _series.value

            }
        }
    }
}