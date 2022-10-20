package com.ezamora.test.api.mappers

import com.ezamora.test.api.SeriesResponse
import javax.inject.Inject

class ApiSeriesResponseMapper @Inject constructor(
    private val apiSeriesDataMapper: ApiSeriesDataMapper
): ApiMapper<SeriesResponse?, com.ezamora.test.domain.SeriesResponse> {

    override fun mapToDomain(apiEntity: SeriesResponse?): com.ezamora.test.domain.SeriesResponse {
        return com.ezamora.test.domain.SeriesResponse(
            code = apiEntity?.code ?: 0,
            data = apiSeriesDataMapper.mapToDomain(apiEntity?.seriesData)
        )
    }
}