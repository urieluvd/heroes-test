package com.ezamora.test.api.mappers

import com.ezamora.test.api.Thumbnail
import javax.inject.Inject

class ApiThumbnailMapper @Inject constructor(): ApiMapper<Thumbnail?, com.ezamora.test.domain.Thumbnail> {

    override fun mapToDomain(apiEntity: Thumbnail?): com.ezamora.test.domain.Thumbnail {
        return com.ezamora.test.domain.Thumbnail(
            path = apiEntity?.path ?: "",
            extension = apiEntity?.extension ?: ""
        )
    }
}