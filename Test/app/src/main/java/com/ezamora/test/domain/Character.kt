package com.ezamora.test.domain


data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: ComicBriefs,
    val series: SeriesBriefs,
    val stories: StoryBriefs
)

class CharacterResponse(
    val code: Int,
    val data: CharactersData
)

data class CharactersData(
    val limit: Int,
    val results: List<Character>
)