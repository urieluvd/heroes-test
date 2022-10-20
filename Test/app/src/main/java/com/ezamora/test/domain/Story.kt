package com.ezamora.test.domain

class Story (
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail
)

data class StoriesResponse(
    val code: Int,
    val data: StoriesData
)

data class StoriesData(
    val limit: Int,
    val results: List<Story>
)

data class StoryBrief(
    val resourceURI: String,
    val name: String
)

data class StoryBriefs(
    val available: Int,
    val collectionURI: String,
    val items: List<StoryBrief>
)