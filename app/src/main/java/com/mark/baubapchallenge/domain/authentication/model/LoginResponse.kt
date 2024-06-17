package com.mark.baubapchallenge.domain.authentication.model

import com.mark.baubapchallenge.data.remote.dtos.ProfileDTO
import com.mark.baubapchallenge.domain.model.Profile
import com.mark.baubapchallenge.domain.model.converter.toBusiness

/**
 * Login Response
 */
abstract class LoginResponse {

    var profile: Profile? = null
        protected set

    class Logged(profileDTO: ProfileDTO) : LoginResponse() {
        init {
            super.profile = profileDTO.toBusiness()
        }
    }

    class Unlogged : LoginResponse()
}