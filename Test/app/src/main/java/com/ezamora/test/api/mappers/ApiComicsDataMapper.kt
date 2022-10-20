package com.ezamora.test.api.mappers

import com.ezamora.test.api.ComicsData
import javax.inject.Inject

class ApiComicsDataMapper @Inject constructor(
    private val apiComicsMapper: ApiComicsMapper
): ApiMapper<ComicsData?, com.ezamora.test.domain.ComicsData> {

    override fun mapToDomain(apiEntity: ComicsData?): com.ezamora.test.domain.ComicsData {
        return com.ezamora.test.domain.ComicsData(
            limit = apiEntity?.limit ?: 0,
            results =apiEntity?.results?.map { apiComicsMapper.mapToDomain(it) } ?: emptyList()
        )
    }
}