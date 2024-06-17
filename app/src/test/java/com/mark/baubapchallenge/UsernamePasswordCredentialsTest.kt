package com.mark.baubapchallenge

import com.mark.baubapchallenge.domain.authentication.model.UsernamePasswordCredentials
import org.junit.Assert
import org.junit.Test

class UsernamePasswordCredentialsTest {

    @Test
    fun givenUsernamePasswordValidCredentials_whenValidate_ReturnTrue(){
        Assert.assertTrue(UsernamePasswordCredentials("Juan", "Perez").isValid())
    }

    @Test
    fun givenUsernamePasswordInvalidCredentialsUsername_whenValidate_ReturnFalse(){
        Assert.assertFalse(UsernamePasswordCredentials("", "Perez").isValid())
    }

    @Test
    fun givenUsernamePasswordInvalidCredentialsPassword_whenValidate_ReturnFalse(){
        Assert.assertFalse(UsernamePasswordCredentials("Juan", "").isValid())
    }
}