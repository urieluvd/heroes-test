package com.ezamora.test.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ezamora.test.api.*
import com.ezamora.test.api.mappers.ApiCharactersResponseMapper
import com.ezamora.test.api.mappers.ApiComicsResponseMapper
import com.ezamora.test.api.mappers.ApiSeriesResponseMapper
import com.ezamora.test.api.mappers.ApiStoriesResponseMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*
import retrofit2.Response

class HeroesRepositoryTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()
    // private val heroesRepository: HeroesRepository = mock()

    lateinit var heroesApi: HeroesApi
    lateinit var charactersResponseMapper: ApiCharactersResponseMapper
    lateinit var heroesRepository: HeroesRepository
    lateinit var comicsResponseMapper: ApiComicsResponseMapper
    lateinit var seriesResponseMapper: ApiSeriesResponseMapper
    lateinit var storiesResponseMapper: ApiStoriesResponseMapper

    @Before
    fun setUp(){
        heroesApi = mock()
        charactersResponseMapper = mock()
        comicsResponseMapper = mock()
        seriesResponseMapper = mock()
        storiesResponseMapper = mock()
        heroesRepository = HeroesRepository(heroesApi, charactersResponseMapper, comicsResponseMapper, seriesResponseMapper, storiesResponseMapper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun shouldCall_apiGetHeroes() = runTest{

        whenever(heroesApi.getCharacters(any())).thenReturn(
            Response.success(CharactersResponse(200, CharactersData(20, emptyList())))
        )

        heroesRepository.getCharacters(20)

        verify(heroesApi).getCharacters(any())
    }

    @Test
    fun shouldCall_apiGetHero() = runTest{

            whenever(heroesApi.getCharacter(any())).thenReturn(
                Response.success(
                    CharactersResponse(
                        200,
                        CharactersData(
                            200,
                            listOf(
                                Character(
                                    0,
                                    "",
                                    "",
                                    Thumbnail("", ""),
                                    ComicsBriefs(0, "", emptyList(), 0),
                                    SeriesBriefs(0, "", emptyList(), 0),
                                    StoryBriefs(0, "", emptyList(), 0)
                                )
                            )
                        )
                    )
                )
            )

            heroesRepository.getCharacter(20)
            verify(heroesApi).getCharacter(any())
        }

    @Test
    fun shouldCall_apiGetHeroComics() = runTest {

        whenever(heroesApi.getCharacterComics(any(), any())).thenReturn(
            Response.success(
                ComicsResponse(
                    200,
                    ComicsData(
                        20,
                        listOf()
                    )
                )
            )
        )

        heroesRepository.getCharacterComics(1011334, 0)

        verify(heroesApi).getCharacterComics(any(), any())
    }
}