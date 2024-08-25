package com.sonusourav.atlys.di

import com.sonusourav.atlys.data.repository.MovieRepositoryImpl
import com.sonusourav.atlys.data.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TmDbRepositoryBind {

    @Binds
    @Singleton
    abstract fun bindRepository(tmDbRepositoryImpl: MovieRepositoryImpl): MovieRepository

}