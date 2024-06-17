package com.mark.baubapchallenge.di

import com.mark.baubapchallenge.data.LoginRepository
import com.mark.baubapchallenge.domain.authentication.AuthenticationProviderManager
import com.mark.baubapchallenge.domain.authentication.model.AuthProvider
import com.mark.baubapchallenge.domain.authentication.model.LoginCredentials
import com.mark.baubapchallenge.domain.authentication.usecase.AuthenticationProviderUseCase
import com.mark.baubapchallenge.domain.authentication.usecase.InternalAuthenticationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
class AuthenticationModule {

    @Provides
    @Singleton
    fun provideAuthenticationProviderManager(
        loginRepository: LoginRepository
    ): AuthenticationProviderManager {
        val providersMap =
            mutableMapOf<AuthProvider, AuthenticationProviderUseCase<out LoginCredentials>>()
        with(InternalAuthenticationUseCase(loginRepository)) {
            providersMap.put(
                this.getAuthProviderType(),
                this
            )
        }
        return AuthenticationProviderManager(providersMap)
    }


}