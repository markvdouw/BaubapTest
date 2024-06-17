package com.mark.baubapchallenge

import app.cash.turbine.test
import com.mark.baubapchallenge.data.remote.NetworkError
import com.mark.baubapchallenge.data.remote.dtos.ProfileDTO
import com.mark.baubapchallenge.domain.authentication.model.LoginResponse
import com.mark.baubapchallenge.domain.authentication.model.UsernamePasswordCredentials
import com.mark.baubapchallenge.domain.authentication.usecase.InternalAuthenticationUseCase
import com.mark.baubapchallenge.domain.model.base.State
import com.mark.baubapchallenge.viewmodel.LoginInternalViewModel
import com.mark.baubapchallenge.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
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
class InternalLoginViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    lateinit var viewModel: LoginInternalViewModel

    @Mock
    lateinit var usecase: InternalAuthenticationUseCase

    private fun getSuccessResponseWithValue(value: String = "test") =
        LoginResponse.Logged(ProfileDTO(value))

    private val credentials = UsernamePasswordCredentials("Juan", "Perez")

    @Test
    fun givenValidCredentials_whenLogin_UpdateStateFlowWithSuccessResult() {
        runTest {
            val successResponse = getSuccessResponseWithValue(credentials.username)
            Mockito.`when`(usecase.login(credentials)).thenReturn(successResponse)
            viewModel.login(credentials)
            viewModel.loginState.test {
                assertEquals(State.Result(successResponse), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            Mockito.verify(usecase, Mockito.times(1)).login(credentials)
        }
    }

    @Test
    fun givenInvalidCredentials_whenLogin_UpdateStateFlowWithErrorResult() {
        runTest {
            val error = NetworkError("test")
            Mockito.`when`(usecase.login(credentials)).thenThrow(error)
            viewModel.login(credentials)
            viewModel.loginState.test {
                Assert.assertEquals(State.Error(error), awaitItem())
                Assert.assertEquals(error.message, "test")
                cancelAndIgnoreRemainingEvents()
            }
            Mockito.verify(usecase, Mockito.times(1)).login(credentials)
        }
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = LoginInternalViewModel(usecase)
    }

}
