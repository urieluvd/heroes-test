package com.ezamora.test.api.mappers

import com.ezamora.test.api.Story
import javax.inject.Inject

class ApiStoriesMapper @Inject constructor(
    private val thumbnailMapper: ApiThumbnailMapper
): ApiMapper<Story?, com.ezamora.test.domain.Story> {

    override fun mapToDomain(apiEntity: Story?): com.ezamora.test.domain.Story {
        return com.ezamora.test.domain.Story(
            id = apiEntity?.id ?: 0,
            title = apiEntity?.title.orEmpty(),
            description = apiEntity?.description.orEmpty(),
            thumbnail = thumbnailMapper.mapToDomain(apiEntity?.thumbnail)
        )
    }
}