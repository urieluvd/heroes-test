package com.ezamora.test.api

import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data") var charactersData: CharactersData
)

data class CharactersData(
    @SerializedName("limit") val limit: Int,
    @SerializedName("results") val results: List<Character>
)

data class Character(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") var description: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
    @SerializedName("comics") val comics: ComicsBriefs?,
    @SerializedName("series") val series: SeriesBriefs?,
    @SerializedName("stories") val stories: StoryBriefs?,
)

data class Thumbnail(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String,
)

data class ComicsResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data") var comicsData: ComicsData
)

data class ComicsData(
    @SerializedName("limit") val limit: Int,
    @SerializedName("results") val results: List<Comic>
)
data class Comic(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("thumbnail") val thumbnail: Thumbnail?
)

data class ComicsBriefs(
    @SerializedName("available") val available : Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: List<ComicBrief>,
    @SerializedName("returned") val returned: Int
)

data class ComicBrief(
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("name") val name: String?,
)


data class SeriesResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data") var seriesData: SeriesData
)

data class SeriesData(
    @SerializedName("limit") val limit: Int,
    @SerializedName("results") val results: List<Series>
)

data class Series(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail?
)

data class SeriesBriefs(
    @SerializedName("available") val available : Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: List<SeriesBrief>,
    @SerializedName("returned") val returned: Int
)

data class SeriesBrief(
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String,
)

data class StoriesResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data") var storiesData: StoriesData
)

data class StoriesData(
    @SerializedName("limit") val limit: Int,
    @SerializedName("results") val results: List<Story>
)

data class StoryBriefs(
    @SerializedName("available") val available : Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: List<StoryBrief>,
    @SerializedName("returned") val returned: Int
)

data class StoryBrief(
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String,
)

data class Story(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail?
)

data class EventsWrapper(
    @SerializedName("available") val available : Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: List<EventBrief>,
    @SerializedName("returned") val returned: Int
)

data class EventBrief(
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String,
)