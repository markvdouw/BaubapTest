package com.mark.baubapchallenge.data.remote.dtos

/**
 * Login response DTO to deserialize from server
 * The profile object within it WILL NOT BE NULL if the user is successfull
 * and the user logged in
 */
data class LoginResponseDTO(val profile: ProfileDTO?)