package com.tws.moments.ui.di

import com.tws.moments.data.api.MomentService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Reusable
    @Provides
    fun provideDogApi(retrofit: Retrofit): MomentService {
        return retrofit.create(MomentService::class.java)
    }
}
