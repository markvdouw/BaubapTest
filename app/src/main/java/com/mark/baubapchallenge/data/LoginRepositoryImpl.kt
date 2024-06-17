package com.mark.baubapchallenge.data

import com.mark.baubap_test.data.remote.sources.LoginApiClient
import com.mark.baubapchallenge.domain.authentication.model.LoginResponse
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginApiClient: LoginApiClient) :
    LoginRepository {

    override suspend fun login(username: String, password: String): LoginResponse =
        with(loginApiClient.login(username, password)) {
            this.profile?.let { LoginResponse.Logged(it) } ?: run { LoginResponse.Unlogged() }
        }
}