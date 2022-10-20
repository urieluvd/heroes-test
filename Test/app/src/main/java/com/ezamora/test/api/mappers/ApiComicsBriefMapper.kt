package com.ezamora.test.api.mappers

import com.ezamora.test.api.ComicBrief
import javax.inject.Inject

class ApiComicsBriefMapper @Inject constructor(): ApiMapper<ComicBrief?, com.ezamora.test.domain.ComicBrief> {

    override fun mapToDomain(apiEntity: ComicBrief?): com.ezamora.test.domain.ComicBrief {
        return com.ezamora.test.domain.ComicBrief(
            resourceURI = apiEntity?.resourceURI.orEmpty(),
            name = apiEntity?.name.orEmpty(),
        )
    }
}