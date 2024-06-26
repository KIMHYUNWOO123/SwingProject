package com.example.swingproject.di

import com.example.data.RepositoryImpl
import com.example.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRemoteRepository(repositoryImpl: RepositoryImpl): Repository
}