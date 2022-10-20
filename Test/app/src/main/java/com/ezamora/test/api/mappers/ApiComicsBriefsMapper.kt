package com.ezamora.test.api.mappers

import com.ezamora.test.api.ComicsBriefs
import javax.inject.Inject

class ApiComicsBriefsMapper @Inject constructor(
    private val apiComicsBriefMapper: ApiComicsBriefMapper
): ApiMapper<ComicsBriefs?, com.ezamora.test.domain.ComicBriefs> {

    override fun mapToDomain(apiEntity: ComicsBriefs?): com.ezamora.test.domain.ComicBriefs {
        return com.ezamora.test.domain.ComicBriefs(
            available = apiEntity?.available ?: 0,
            collectionURI = apiEntity?.collectionURI.orEmpty(),
            items = apiEntity?.items?.map {  apiComicsBriefMapper.mapToDomain(it) } ?: emptyList()
        )
    }
}