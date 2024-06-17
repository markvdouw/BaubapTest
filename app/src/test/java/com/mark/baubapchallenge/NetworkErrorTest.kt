package com.mark.baubapchallenge

import com.mark.baubapchallenge.data.remote.NetworkError
import com.mark.baubapchallenge.data.remote.toNetworkError
import org.junit.Assert
import org.junit.Test
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkErrorTest {

    @Test
    fun givenASocketTimeException_whenCallToNetworkError_ReturnNetworkErrorWIthText() {
        val exception = SocketTimeoutException()
        val networkError = exception.toNetworkError()
        Assert.assertTrue(networkError is NetworkError)
        Assert.assertEquals("connection error!", networkError.message)
    }

    @Test
    fun givenAConnectException_whenCallToNetworkError_ReturnNetworkErrorWIthText() {
        val exception = ConnectException()
        val networkError = exception.toNetworkError()
        Assert.assertTrue(networkError is NetworkError)
        Assert.assertEquals("no internet access!", networkError.message)
    }

    @Test
    fun givenAUnknownHostException_whenCallToNetworkError_ReturnNetworkErrorWIthText() {
        val exception = UnknownHostException()
        val networkError = exception.toNetworkError()
        Assert.assertTrue(networkError is NetworkError)
        Assert.assertEquals("no internet access!", networkError.message)
    }

    @Test
    fun givenAnyException_whenCallToNetworkError_ReturnNetworkErrorWIthText() {
        val exception = RuntimeException()
        val networkError = exception.toNetworkError()
        Assert.assertTrue(networkError is NetworkError)
        Assert.assertEquals("internal error", networkError.message)
    }
}