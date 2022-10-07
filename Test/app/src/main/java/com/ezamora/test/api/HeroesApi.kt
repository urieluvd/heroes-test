package com.ezamora.test.api

import com.ezamora.test.globals.Constants
import com.ezamora.test.views.models.HeroesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesApi {
    @GET("/v1/public/characters")
    suspend fun getHeroes(
        @Query("apikey") apiKey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.TIMESTAMP,
        @Query("hash") hash: String,
        @Query("offset") offset: Int
    ) : Response<HeroesModel>
}