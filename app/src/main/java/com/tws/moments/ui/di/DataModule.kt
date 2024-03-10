package com.tws.moments.ui.di

import com.tws.moments.data.MomentRepository
import com.tws.moments.data.Remote
import com.tws.moments.data.api.RemoteImpl
import com.tws.moments.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun provideRepository(repository: MomentRepository): Repository {
        return repository
    }

    @Singleton
    @Provides
    fun provideRemote(dataSourceRemote: RemoteImpl): Remote {
        return dataSourceRemote
    }
}
