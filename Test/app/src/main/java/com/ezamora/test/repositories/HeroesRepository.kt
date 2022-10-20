package com.ezamora.test.repositories

import com.ezamora.test.api.HeroesApi
import com.ezamora.test.domain.SeriesResponse
import com.ezamora.test.domain.StoriesResponse
import com.ezamora.test.api.mappers.ApiCharactersResponseMapper
import com.ezamora.test.api.mappers.ApiComicsResponseMapper
import com.ezamora.test.api.mappers.ApiSeriesResponseMapper
import com.ezamora.test.api.mappers.ApiStoriesResponseMapper
import com.ezamora.test.domain.CharacterResponse
import com.ezamora.test.domain.ComicsResponse
import javax.inject.Inject

class HeroesRepository
@Inject constructor(
    private val heroesApi: HeroesApi,
    private val charactersMapper: ApiCharactersResponseMapper,
    private val comicsMapper: ApiComicsResponseMapper,
    private val seriesMapper: ApiSeriesResponseMapper,
    private val storiesMapper: ApiStoriesResponseMapper
    ) {
    suspend fun getCharacters(offset: Int): CharacterResponse?{
        val request = heroesApi.getCharacters(offset = offset)
        if (request.code() == 200){
            return charactersMapper.mapToDomain(request.body())
        }else{
            return null
        }
    }

    suspend fun getCharacter(id: Int): CharacterResponse?{
        val request = heroesApi.getCharacter(characterId = id)
        if (request.code() == 200){
            return charactersMapper.mapToDomain(request.body())
        }else{
            return null
        }
    }

    suspend fun getCharacterComics(id: Int, offset: Int): ComicsResponse?{
        val request = heroesApi.getCharacterComics(characterId = id, offset = offset)
        if (request.code() == 200){
            return comicsMapper.mapToDomain(request.body())
        }else{
            return null
        }
    }

    suspend fun getCharacterSeries(id: Int, offset: Int): SeriesResponse?{
        val request = heroesApi.getCharacterSeries(characterId = id, offset = offset)
        if (request.code() == 200){
            return seriesMapper.mapToDomain(request.body())
        }else{
            return null
        }
    }

    suspend fun getCharacterStories(id: Int, offset: Int): StoriesResponse?{
        val request = heroesApi.getCharacterStories(characterId = id, offset = offset)
        if (request.code() == 200){
            return storiesMapper.mapToDomain(request.body())
        }else{
            return null
        }
    }
}