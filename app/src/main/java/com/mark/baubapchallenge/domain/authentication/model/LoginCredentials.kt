package com.mark.baubapchallenge.domain.authentication.model

/**
 * This class models the params needed within the authentication process
 */
abstract class LoginCredentials(var user: String = "") {

    /**
     * Checks if a specific credential object (based on multiple params) is valid or not
     * based on the business logic
     * @return true if the condition is met and therefore the request can be made, else false
     *
     * TODO: Add regex if necessary
     */
    open fun isValid(): Boolean = user.isNotBlank()
}