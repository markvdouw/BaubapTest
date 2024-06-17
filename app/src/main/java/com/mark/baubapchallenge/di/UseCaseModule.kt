package com.mark.baubapchallenge.di

import com.mark.baubapchallenge.domain.authentication.AuthenticationProviderManager
import com.mark.baubapchallenge.domain.authentication.model.AuthProvider
import com.mark.baubapchallenge.domain.authentication.usecase.InternalAuthenticationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideInternalLoginUseCase(authenticationProviderManager: AuthenticationProviderManager):
            InternalAuthenticationUseCase? {
        return authenticationProviderManager.getUseCase(AuthProvider.USERNAME_PASSWORD)
    }
}