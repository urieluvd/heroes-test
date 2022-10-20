package com.ezamora.test.api.mappers

import com.ezamora.test.api.SeriesData
import javax.inject.Inject

class ApiSeriesDataMapper @Inject constructor(
    private val apiSeriesMapper: ApiSeriesMapper
): ApiMapper<SeriesData?, com.ezamora.test.domain.SeriesData> {

    override fun mapToDomain(apiEntity: SeriesData?): com.ezamora.test.domain.SeriesData {
        return com.ezamora.test.domain.SeriesData(
            limit = apiEntity?.limit ?: 0,
            results =apiEntity?.results?.map { apiSeriesMapper.mapToDomain(it) } ?: emptyList()
        )
    }
}