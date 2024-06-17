package com.mark.baubapchallenge.domain.authentication

import com.mark.baubapchallenge.domain.authentication.model.AuthProvider
import com.mark.baubapchallenge.domain.authentication.model.LoginCredentials
import com.mark.baubapchallenge.domain.authentication.usecase.AuthenticationProviderUseCase

/**
 * Authentication manager injects a provider type, provider usecase map
 */
class AuthenticationProviderManager(private val providersMap: Map<AuthProvider, AuthenticationProviderUseCase<out LoginCredentials>>) {

    /**
     * Returns a casted use case for each provider based on its type
     */
    fun <C : AuthenticationProviderUseCase<out LoginCredentials>> getUseCase(provider: AuthProvider): C? =
        providersMap[provider] as C?
}