package com.mark.baubap_test.data.remote.sources

import com.mark.baubapchallenge.data.remote.NetworkError
import com.mark.baubapchallenge.data.remote.dtos.LoginResponseDTO

interface LoginApiClient {
    @Throws(NetworkError::class)
    suspend fun login(username: String, password: String): LoginResponseDTO
}