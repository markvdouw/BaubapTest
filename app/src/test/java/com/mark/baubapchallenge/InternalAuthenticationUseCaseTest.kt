package com.mark.baubapchallenge

import com.mark.baubapchallenge.data.LoginRepository
import com.mark.baubapchallenge.data.remote.NetworkError
import com.mark.baubapchallenge.data.remote.dtos.ProfileDTO
import com.mark.baubapchallenge.domain.authentication.model.AuthProvider
import com.mark.baubapchallenge.domain.authentication.model.LoginResponse
import com.mark.baubapchallenge.domain.authentication.model.UsernamePasswordCredentials
import com.mark.baubapchallenge.domain.authentication.usecase.InternalAuthenticationUseCase
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
class InternalAuthenticationUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    lateinit var usecase: InternalAuthenticationUseCase

    @Mock
    lateinit var repository: LoginRepository
    private val credentials = UsernamePasswordCredentials("Juan", "Perez")

    @Test
    fun givenValidCredentials_whenLogin_ReturnLoginResponse() {
        runTest {
            val response = LoginResponse.Logged(ProfileDTO(credentials.username))
            Mockito.`when`(usecase.login(credentials)).thenReturn(response)
            val usecaseResult = usecase.login(credentials)
            Assert.assertEquals(response, usecaseResult)
            Mockito.verify(repository, Mockito.times(1))
                .login(credentials.username, credentials.password)
        }
    }

    @Test(expected = NetworkError::class)
    fun givenInvalidCredentials_whenLogin_ThrowsNetworkException() {
        runTest {
            val error = NetworkError("error")
            Mockito.`when`(usecase.login(credentials)).thenThrow(error)
            usecase.login(credentials)
            Mockito.verify(repository, Mockito.times(1))
                .login(credentials.username, credentials.password)
        }
    }

    @Test
    fun givenInstance_whenAuthProvider_ReturnUsernamePassword(){
        Assert.assertEquals(AuthProvider.USERNAME_PASSWORD, usecase.getAuthProviderType())
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        usecase = InternalAuthenticationUseCase(repository)
    }

}