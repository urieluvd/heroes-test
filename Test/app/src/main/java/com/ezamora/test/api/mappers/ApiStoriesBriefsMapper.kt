package com.ezamora.test.api.mappers

import com.ezamora.test.api.StoryBriefs
import javax.inject.Inject

class ApiStoriesBriefsMapper @Inject constructor(
    private val apiStoriesBriefMapper: ApiStoriesBriefMapper
): ApiMapper<StoryBriefs?, com.ezamora.test.domain.StoryBriefs> {

    override fun mapToDomain(apiEntity: StoryBriefs?): com.ezamora.test.domain.StoryBriefs {
        return com.ezamora.test.domain.StoryBriefs(
            available = apiEntity?.available ?: 0,
            collectionURI = apiEntity?.collectionURI.orEmpty(),
            items = apiEntity?.items?.map {  apiStoriesBriefMapper.mapToDomain(it) } ?: emptyList()
        )
    }
}