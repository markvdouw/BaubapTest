package com.mark.baubapchallenge.domain.authentication.usecase

import com.mark.baubapchallenge.data.LoginRepository
import com.mark.baubapchallenge.data.remote.NetworkError
import com.mark.baubapchallenge.domain.authentication.model.AuthProvider
import com.mark.baubapchallenge.domain.authentication.model.LoginResponse
import com.mark.baubapchallenge.domain.authentication.model.UsernamePasswordCredentials
import com.mark.baubapchallenge.domain.authentication.usecase.AuthenticationProviderUseCase

/**
 * Login use case to log in based on a username and password pair
 */
open class InternalAuthenticationUseCase(private val loginRepository: LoginRepository) :
    AuthenticationProviderUseCase<UsernamePasswordCredentials> {

    @Throws(NetworkError::class)
    override suspend fun login(credentials: UsernamePasswordCredentials): LoginResponse {
        return try {
            val response = loginRepository.login(credentials.username, credentials.password)
            response
        } catch (networkError: NetworkError) {
            throw networkError
        }
    }

    override fun getAuthProviderType(): AuthProvider = AuthProvider.USERNAME_PASSWORD
}