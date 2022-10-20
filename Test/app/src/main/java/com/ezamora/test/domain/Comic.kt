package com.ezamora.test.domain


class Comic (
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail
)

data class ComicsResponse(
    val code: Int,
    val data: ComicsData
)

data class ComicsData(
    val limit: Int,
    val results: List<Comic>
)

class Comics(
    val code: Int,
    val data: ComicBriefs
)

data class ComicBrief(
    val resourceURI: String,
    val name: String
)

data class ComicBriefs(
    val available: Int,
    val collectionURI: String,
    val items: List<ComicBrief>
)