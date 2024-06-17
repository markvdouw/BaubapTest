package com.mark.baubapchallenge.domain.authentication.usecase

import com.mark.baubapchallenge.domain.authentication.model.AuthProvider
import com.mark.baubapchallenge.domain.authentication.model.LoginCredentials
import com.mark.baubapchallenge.domain.authentication.model.LoginResponse


/**
 * Represent the operations an authentication provider can do as a use case
 */
interface AuthenticationProviderUseCase<L : LoginCredentials> {
    /**
     * The login function will authenticate a used based on username and password,
     * updating the session within the global state and return the login response
     * if the request completes successfully.
     * This function will throw an NetworkError exception if the request can't complete
     *
     * @param  credentials
     * @return LoginResponse or exception will be thrown
     */
    suspend fun login(credentials: L): LoginResponse

    /**
     * Authentication Provider
     */
    fun getAuthProviderType(): AuthProvider
}