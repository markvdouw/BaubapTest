package com.mark.baubapchallenge.domain.model.converter

import com.mark.baubapchallenge.data.remote.dtos.ProfileDTO
import com.mark.baubapchallenge.domain.model.Profile


/**
 * Converts a profile dto to the profile business model
 * @return profile business model
 */
fun ProfileDTO.toBusiness(): Profile = Profile(this.name)