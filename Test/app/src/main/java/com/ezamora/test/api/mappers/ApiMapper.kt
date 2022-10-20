package com.ezamora.test.api.mappers

interface ApiMapper<E, D> {

    fun mapToDomain(apiEntity: E): D
}