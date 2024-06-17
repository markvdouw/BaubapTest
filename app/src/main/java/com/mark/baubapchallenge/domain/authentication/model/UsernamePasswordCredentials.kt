package com.mark.baubapchallenge.domain.authentication.model

/**
 * Abstraction to model Credentials for username, password login use case
 */
data class UsernamePasswordCredentials(val username: String = "", val password: String = "") :
    LoginCredentials(username) {

    override fun isValid(): Boolean = super.isValid() && password.isNotBlank()
}