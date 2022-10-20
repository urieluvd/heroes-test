package com.ezamora.test.api.mappers

import com.ezamora.test.api.StoriesData
import javax.inject.Inject

class ApiStoriesDataMapper @Inject constructor(
    private val apiStoriesMapper: ApiStoriesMapper
): ApiMapper<StoriesData?, com.ezamora.test.domain.StoriesData> {

    override fun mapToDomain(apiEntity: StoriesData?): com.ezamora.test.domain.StoriesData {
        return com.ezamora.test.domain.StoriesData(
            limit = apiEntity?.limit ?: 0,
            results =apiEntity?.results?.map { apiStoriesMapper.mapToDomain(it) } ?: emptyList()
        )
    }
}