package com.ezamora.test.api.mappers

import javax.inject.Inject

class ApiCharactersMapper @Inject constructor(
    private val apiThumbnailMapper: ApiThumbnailMapper,
    private val apiComicsBriefsMapper: ApiComicsBriefsMapper,
    private val apiSeriesBriefsMapper: ApiSeriesBriefsMapper,
    private val apiStoriesBriefsMapper: ApiStoriesBriefsMapper
): ApiMapper<com.ezamora.test.api.Character?, com.ezamora.test.domain.Character> {

    override fun mapToDomain(apiEntity: com.ezamora.test.api.Character?): com.ezamora.test.domain.Character {
        return com.ezamora.test.domain.Character(
            id = apiEntity?.id ?: 0,
            name = apiEntity?.name.orEmpty(),
            description = apiEntity?.description.orEmpty(),
            thumbnail = apiThumbnailMapper.mapToDomain(apiEntity?.thumbnail),
            comics = apiComicsBriefsMapper.mapToDomain(apiEntity?.comics),
            series = apiSeriesBriefsMapper.mapToDomain(apiEntity?.series),
            stories = apiStoriesBriefsMapper.mapToDomain(apiEntity?.stories)
        )
    }
}