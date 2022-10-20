package com.ezamora.test.api.mappers

import com.ezamora.test.api.Comic
import javax.inject.Inject

class ApiComicsMapper @Inject constructor(
    private val thumbnailMapper: ApiThumbnailMapper
): ApiMapper<Comic?, com.ezamora.test.domain.Comic> {

    override fun mapToDomain(apiEntity: Comic?): com.ezamora.test.domain.Comic {
        return com.ezamora.test.domain.Comic(
            id = apiEntity?.id ?: 0,
            title = apiEntity?.title.orEmpty(),
            description = apiEntity?.description.orEmpty(),
            thumbnail = thumbnailMapper.mapToDomain(apiEntity?.thumbnail)
        )
    }
}