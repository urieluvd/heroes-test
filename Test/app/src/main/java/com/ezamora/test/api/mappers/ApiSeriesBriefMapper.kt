package com.ezamora.test.api.mappers

import com.ezamora.test.api.SeriesBrief
import javax.inject.Inject

class ApiSeriesBriefMapper @Inject constructor(): ApiMapper<SeriesBrief?, com.ezamora.test.domain.SeriesBrief> {

    override fun mapToDomain(apiEntity: SeriesBrief?): com.ezamora.test.domain.SeriesBrief {
        return com.ezamora.test.domain.SeriesBrief(
            resourceURI = apiEntity?.resourceURI.orEmpty(),
            name = apiEntity?.name.orEmpty(),
        )
    }
}