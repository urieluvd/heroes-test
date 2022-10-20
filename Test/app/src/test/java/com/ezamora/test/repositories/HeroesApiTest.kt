package com.ezamora.test.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ezamora.test.api.CharactersData
import com.ezamora.test.api.CharactersResponse
import com.ezamora.test.api.HeroesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*
import retrofit2.Response

class HeroesApiTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    lateinit var heroesApi: HeroesApi
    lateinit var spyHeroesApi: HeroesApi

    @Before
    fun setUp(){
        heroesApi = mock()
        spyHeroesApi = spy(heroesApi)
    }
    @Test
    fun shouldGetAHeroesResponse() = runTest{

        doReturn(Response.success(CharactersResponse(200, CharactersData(20, emptyList())))).whenever(spyHeroesApi).getCharacters(any())
        val response = spyHeroesApi.getCharacters(20)
        Assert.assertNotNull(response)
    }

}