package com.mark.baubapchallenge.di

import com.mark.baubapchallenge.data.LoginRepository
import com.mark.baubapchallenge.data.LoginRepositoryImpl
import com.mark.baubap_test.data.remote.sources.LoginApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(loginApiClient: LoginApiClient): LoginRepository =
        LoginRepositoryImpl(loginApiClient)

}
