package com.ezamora.test.api.mappers

import com.ezamora.test.api.CharactersResponse
import javax.inject.Inject

class ApiCharactersResponseMapper @Inject constructor(
    private val apiCharactersDataMapper: ApiCharactersDataMapper
): ApiMapper<CharactersResponse?, com.ezamora.test.domain.CharacterResponse> {

    override fun mapToDomain(apiEntity: CharactersResponse?): com.ezamora.test.domain.CharacterResponse {
        return com.ezamora.test.domain.CharacterResponse(
            code = apiEntity?.code ?: 0,
            data = apiCharactersDataMapper.mapToDomain(apiEntity?.charactersData)
        )
    }
}