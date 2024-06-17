package com.mark.baubapchallenge

import com.mark.baubapchallenge.data.LoginRepository
import com.mark.baubapchallenge.data.LoginRepositoryImpl
import com.mark.baubapchallenge.data.remote.NetworkError
import com.mark.baubapchallenge.data.remote.dtos.LoginResponseDTO
import com.mark.baubapchallenge.data.remote.dtos.ProfileDTO
import com.mark.baubap_test.data.remote.sources.LoginApiClient
import com.mark.baubapchallenge.domain.authentication.model.LoginResponse
import com.mark.baubapchallenge.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    lateinit var loginRepository: LoginRepository

    @Mock
    lateinit var loginApiClient: LoginApiClient

    private val loginResponseDTO = LoginResponseDTO(ProfileDTO("Juan"))

    @Test
    fun givenValidUsernamePassword_whenLogin_ReturnLoggedLoginResponseWithProfile() {
        runTest {
            Mockito.`when`(loginApiClient.login(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(loginResponseDTO)
            val response = loginRepository.login("Juan", "Perez")
            Assert.assertEquals(
                response.profile, LoginResponse.Logged(loginResponseDTO.profile!!).profile
            )
            Mockito.verify(loginApiClient, Mockito.times(1))
                .login(Mockito.anyString(), Mockito.anyString())
        }
    }

    @Test(expected = NetworkError::class)
    fun givenUsernamePassword_whenLogin_ThrowsNetworkError() {
        runTest {
            Mockito.`when`(loginApiClient.login(Mockito.anyString(), Mockito.anyString()))
                .thenThrow(NetworkError("error"))
            loginApiClient.login("Juan", "Perez")
            Mockito.verify(loginApiClient, Mockito.times(1)).login("Juan", "Perez")
        }
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        loginRepository = LoginRepositoryImpl(loginApiClient)
    }

}