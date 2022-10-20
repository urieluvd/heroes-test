package com.ezamora.test.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeroesApi {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int
    ) : Response<CharactersResponse?>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacter(
        @Path("characterId") characterId: Int
    ) : Response<CharactersResponse?>

    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getCharacterComics(
        @Path("characterId") characterId: Int,
        @Query("offset") offset: Int
    ) : Response<ComicsResponse>

    @GET("/v1/public/characters/{characterId}/series")
    suspend fun getCharacterSeries(
        @Path("characterId") characterId: Int,
        @Query("offset") offset: Int
    ) : Response<SeriesResponse>

    @GET("/v1/public/characters/{characterId}/stories")
    suspend fun getCharacterStories(
        @Path("characterId") characterId: Int,
        @Query("offset") offset: Int
    ) : Response<StoriesResponse>

}