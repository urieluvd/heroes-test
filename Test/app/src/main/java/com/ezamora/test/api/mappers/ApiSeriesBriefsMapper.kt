package com.ezamora.test.api.mappers

import com.ezamora.test.api.SeriesBriefs
import javax.inject.Inject

class ApiSeriesBriefsMapper @Inject constructor(
    private val apiSeriesBriefMapper: ApiSeriesBriefMapper
): ApiMapper<SeriesBriefs?, com.ezamora.test.domain.SeriesBriefs> {

    override fun mapToDomain(apiEntity: SeriesBriefs?): com.ezamora.test.domain.SeriesBriefs {
        return com.ezamora.test.domain.SeriesBriefs(
            available = apiEntity?.available ?: 0,
            collectionURI = apiEntity?.collectionURI.orEmpty(),
            items = apiEntity?.items?.map {  apiSeriesBriefMapper.mapToDomain(it) } ?: emptyList()
        )
    }
}