package com.ezamora.test.module

import com.ezamora.test.api.HeroesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroesModule {
    @Singleton
    @Provides
    fun provideHeroesData(retrofit: Retrofit) : HeroesApi =
        retrofit.create(HeroesApi::class.java)
}