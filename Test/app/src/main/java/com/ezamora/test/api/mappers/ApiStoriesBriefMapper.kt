package com.ezamora.test.api.mappers

import com.ezamora.test.api.StoryBrief
import javax.inject.Inject

class ApiStoriesBriefMapper @Inject constructor(): ApiMapper<StoryBrief?, com.ezamora.test.domain.StoryBrief> {

    override fun mapToDomain(apiEntity: StoryBrief?): com.ezamora.test.domain.StoryBrief {
        return com.ezamora.test.domain.StoryBrief(
            resourceURI = apiEntity?.resourceURI.orEmpty(),
            name = apiEntity?.name.orEmpty(),
        )
    }
}