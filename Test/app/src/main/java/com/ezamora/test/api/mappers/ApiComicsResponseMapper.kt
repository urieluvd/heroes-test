package com.ezamora.test.api.mappers

import com.ezamora.test.api.ComicsResponse
import javax.inject.Inject

class ApiComicsResponseMapper @Inject constructor(
    private val apiComicsDataMapper: ApiComicsDataMapper
): ApiMapper<ComicsResponse?, com.ezamora.test.domain.ComicsResponse> {

    override fun mapToDomain(apiEntity: ComicsResponse?): com.ezamora.test.domain.ComicsResponse {
        return com.ezamora.test.domain.ComicsResponse(
            code = apiEntity?.code ?: 0,
            data = apiComicsDataMapper.mapToDomain(apiEntity?.comicsData)
        )
    }
}