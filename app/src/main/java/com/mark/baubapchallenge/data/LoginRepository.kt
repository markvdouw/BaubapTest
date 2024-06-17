package com.mark.baubapchallenge.data

import com.mark.baubapchallenge.data.remote.NetworkError
import com.mark.baubapchallenge.domain.authentication.model.LoginResponse

interface LoginRepository {

    /**
     * Login function called to login a user based on username and password
     * A network error containing a message will be thrown if the request fails.
     * @param username
     * @param password
     * @return LoginResponse
     */
    @Throws(NetworkError::class)
    suspend fun login(username: String, password: String): LoginResponse
}