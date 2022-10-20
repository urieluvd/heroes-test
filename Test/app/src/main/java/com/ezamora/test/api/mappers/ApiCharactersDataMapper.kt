package com.ezamora.test.api.mappers

import com.ezamora.test.api.CharactersData
import com.ezamora.test.api.CharactersResponse
import javax.inject.Inject

class ApiCharactersDataMapper @Inject constructor(
    private val apiCharactersMapper: ApiCharactersMapper
): ApiMapper<CharactersData?, com.ezamora.test.domain.CharactersData> {

    override fun mapToDomain(apiEntity: CharactersData?): com.ezamora.test.domain.CharactersData {
        return com.ezamora.test.domain.CharactersData(
            limit = apiEntity?.limit ?: 0,
            results =apiEntity?.results?.map { apiCharactersMapper.mapToDomain(it) } ?: emptyList()
        )
    }
}