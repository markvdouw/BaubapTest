package com.mark.baubapchallenge.domain.model.base

import com.mark.baubapchallenge.data.remote.NetworkError


/**
 * This class represent the possible states, specifically used for async calls
 */
sealed class State<out T> {
    data object Nothing : State<Nothing>()
    data object Loading : State<Nothing>()
    data class Error(var exception: NetworkError) : State<Nothing>()
    data class Result<T>(var data: T) : State<T>()
}

