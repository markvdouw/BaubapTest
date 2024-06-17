package com.mark.baubapchallenge

import com.mark.baubapchallenge.data.remote.NetworkError
import com.mark.baubapchallenge.data.remote.dtos.LoginResponseDTO
import com.mark.baubap_test.data.remote.sources.LoginApiClient
import com.mark.baubap_test.data.remote.sources.LoginApiClientImpl
import com.mark.baubapchallenge.domain.authentication.model.UsernamePasswordCredentials
import com.mark.baubapchallenge.utils.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LoginApiClientImplTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    lateinit var apiClient: LoginApiClient

    private val credentials = UsernamePasswordCredentials("Juan", "Perez")

    @Test(expected = NetworkError::class)
    fun givenAThrownException_whenLogin_ThrowsANetworkException() {
        runTest {
            Mockito.`when`(apiClient.login(credentials.username, credentials.password))
                .thenThrow(Exception())
            apiClient.login(credentials.username, credentials.password)
        }
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        apiClient = TestLoginApiClientImpl()
    }

}

class TestLoginApiClientImpl : LoginApiClientImpl() {
    override suspend fun login(username: String, password: String): LoginResponseDTO {
        throw NetworkError("")
    }
}