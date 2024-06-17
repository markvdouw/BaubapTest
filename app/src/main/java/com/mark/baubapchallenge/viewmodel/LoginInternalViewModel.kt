package com.mark.baubapchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mark.baubapchallenge.data.remote.NetworkError
import com.mark.baubapchallenge.domain.authentication.model.UsernamePasswordCredentials
import com.mark.baubapchallenge.domain.authentication.usecase.InternalAuthenticationUseCase
import com.mark.baubapchallenge.domain.model.base.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginInternalViewModel @Inject constructor(private val useCase: InternalAuthenticationUseCase?) :
    ViewModel() {
    private val _loginState: MutableStateFlow<State<Any>> = MutableStateFlow(State.Nothing)
    val loginState = _loginState.asStateFlow()

    /**
     * Login function called to login based on specific credentials
     * This function will update the loginState mutable state flow collected in the view
     * The result will be wrapped in a State object.
     * @param credentials
     */
    fun login(credentials: UsernamePasswordCredentials) {
        viewModelScope.launch {
            try {
                useCase?.let {
                    _loginState.value = State.Loading
                    val response = it.login(credentials)
                    _loginState.value = State.Result(response)
                }
            } catch (e: NetworkError) {
                _loginState.value = State.Error(e)
            }
        }
    }

}