package com.ezamora.test.repositories

import com.ezamora.test.api.HeroesApi
import com.ezamora.test.globals.Constants
import com.ezamora.test.views.models.HeroesModel
import okio.internal.commonAsUtf8ToByteArray
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class HeroesRepository
@Inject constructor(
    private val heroesRepository: HeroesApi
    ) {
    suspend fun getCharacteres(
        offset: Int
    ): HeroesModel?{
        val input = "${Constants.TIMESTAMP}${Constants.PRIVATE_KEY}${Constants.API_KEY}"
        val md = MessageDigest.getInstance("MD5")
        val hash = BigInteger(1, md.digest(input.toByteArray())).toString(16)
        val request = heroesRepository.getHeroes(hash = hash, offset = offset)

        if (request.code() == 200){
            return request.body()
        }else{
            return null
        }
    }
}