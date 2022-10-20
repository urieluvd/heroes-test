package com.ezamora.test.api.mappers

import com.ezamora.test.api.StoriesResponse
import javax.inject.Inject

class ApiStoriesResponseMapper @Inject constructor(
    private val apiStoriesDataMapper: ApiStoriesDataMapper
): ApiMapper<StoriesResponse?, com.ezamora.test.domain.StoriesResponse> {

    override fun mapToDomain(apiEntity: StoriesResponse?): com.ezamora.test.domain.StoriesResponse {
        return com.ezamora.test.domain.StoriesResponse(
            code = apiEntity?.code ?: 0,
            data = apiStoriesDataMapper.mapToDomain(apiEntity?.storiesData)
        )
    }
}