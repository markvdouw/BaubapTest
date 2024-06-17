package com.mark.baubapchallenge.di

import com.mark.baubap_test.data.remote.sources.LoginApiClient
import com.mark.baubap_test.data.remote.sources.LoginApiClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
class ApiClientModule {

    @Provides
    fun provideLoginApiClient(): LoginApiClient = LoginApiClientImpl()

}