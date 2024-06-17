package com.mark.baubap_test.data.remote.sources

import com.mark.baubapchallenge.data.remote.dtos.LoginResponseDTO
import com.mark.baubapchallenge.data.remote.dtos.ProfileDTO
import com.mark.baubapchallenge.data.remote.toNetworkError
import kotlinx.coroutines.delay
import javax.inject.Inject

open class LoginApiClientImpl @Inject constructor() : LoginApiClient {

    override suspend fun login(username: String, password: String): LoginResponseDTO {
        delay(3000)
        return try {
            LoginResponseDTO(profile = ProfileDTO("test"))
        } catch (e: Exception) {
            throw e.toNetworkError()
        }
    }
}