package com.ezamora.test.domain

class Series (
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail
)

class SeriesResponse (
    val code: Int,
    val data: SeriesData
)
data class SeriesData(
    val limit: Int,
    val results: List<Series>
)

data class SeriesBrief(
    val resourceURI: String,
    val name: String
)

data class SeriesBriefs(
    val available: Int,
    val collectionURI: String,
    val items: List<SeriesBrief>
)