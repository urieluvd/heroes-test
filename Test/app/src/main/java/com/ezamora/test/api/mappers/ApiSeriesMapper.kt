package com.ezamora.test.api.mappers

import com.ezamora.test.api.Series
import javax.inject.Inject

class ApiSeriesMapper @Inject constructor(
    private val thumbnailMapper: ApiThumbnailMapper
): ApiMapper<Series?, com.ezamora.test.domain.Series> {

    override fun mapToDomain(apiEntity: Series?): com.ezamora.test.domain.Series {
        return com.ezamora.test.domain.Series(
            id = apiEntity?.id ?: 0,
            title = apiEntity?.title.orEmpty(),
            description = apiEntity?.description.orEmpty(),
            thumbnail = thumbnailMapper.mapToDomain(apiEntity?.thumbnail)
        )
    }
}