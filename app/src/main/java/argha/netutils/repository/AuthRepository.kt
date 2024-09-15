package argha.netutils.repository

import argha.netutils.api.AuthAPI
import argha.netutils.models.auth.ApiKeyResponseCreate
import argha.netutils.models.auth.ApiKeyResponseGet
import argha.netutils.models.auth.LoginResponse
import argha.netutils.models.auth.RegisterResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authAPI: AuthAPI) {

    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> = _loginResponse

    private val _registerResponse = MutableStateFlow<RegisterResponse?>(null)
    val registerResponse: StateFlow<RegisterResponse?> = _registerResponse

    private val _apiDetailsResponse = MutableStateFlow<ApiKeyResponseGet?>(null)
    val apiDetailsResponse: StateFlow<ApiKeyResponseGet?> = _apiDetailsResponse

    private val _createApiKeyResponse = MutableStateFlow<ApiKeyResponseCreate?>(null)
    val createApiKeyResponse: StateFlow<ApiKeyResponseCreate?> = _createApiKeyResponse

    suspend fun login(email: String, password: String) {
        val response = authAPI.login(email, password)
        if (response.isSuccessful && response.body() != null) {
            _loginResponse.emit(response.body()!!)
        }
    }

    suspend fun register(email: String, password: String, name: String, username: String) {
        val response = authAPI.register(email, password, name, username)
        if (response.isSuccessful && response.body() != null) {
            _registerResponse.emit(response.body()!!)
        }
    }

    suspend fun getApiDetails(userId: String) {
        val response = authAPI.getApiDetails(userId)
        if (response.isSuccessful && response.body() != null) {
            _apiDetailsResponse.emit(response.body()!!)
        }
    }
    suspend fun createApiKey(userId: String) {
        val response = authAPI.createApiKey(userId)
        if(response.isSuccessful && response.body() != null) {
            _createApiKeyResponse.emit(response.body()!!)
        }
    }
}