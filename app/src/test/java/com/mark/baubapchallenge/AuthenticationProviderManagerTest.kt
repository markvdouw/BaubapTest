package com.mark.baubapchallenge

import com.mark.baubapchallenge.data.LoginRepository
import com.mark.baubapchallenge.domain.authentication.AuthenticationProviderManager
import com.mark.baubapchallenge.domain.authentication.model.AuthProvider
import com.mark.baubapchallenge.domain.authentication.model.LoginCredentials
import com.mark.baubapchallenge.domain.authentication.usecase.AuthenticationProviderUseCase
import com.mark.baubapchallenge.domain.authentication.usecase.InternalAuthenticationUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AuthenticationProviderManagerTest {

    var usecase: InternalAuthenticationUseCase =
        InternalAuthenticationUseCase(Mockito.mock(LoginRepository::class.java))

    private fun getProvidersMap(): Map<AuthProvider, AuthenticationProviderUseCase<out LoginCredentials>> {
        val map = mutableMapOf<AuthProvider, AuthenticationProviderUseCase<out LoginCredentials>>()
        map.put(AuthProvider.USERNAME_PASSWORD, usecase)
        return map
    }

    @Test
    fun givenAnAuthProviderAvailable_whenGetUseCase_ReturnACastedUseCase() {
        val manager = AuthenticationProviderManager(getProvidersMap())
        Assert.assertEquals(
            usecase,
            manager.getUseCase<InternalAuthenticationUseCase>(AuthProvider.USERNAME_PASSWORD)
        )
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }
}