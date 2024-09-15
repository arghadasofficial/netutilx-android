package argha.netutils.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import argha.netutils.models.auth.LoginResponse
import argha.netutils.models.auth.RegisterResponse
import argha.netutils.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginUiState = mutableStateOf(LoginUiState())
    val loginUiState: State<LoginUiState> = _loginUiState

    private val _registerUiState = mutableStateOf(RegisterUiState())
    val registerUiState: State<RegisterUiState> = _registerUiState

    private val loginResponse: StateFlow<LoginResponse?> = authRepository.loginResponse
    private val registerResponse: StateFlow<RegisterResponse?> = authRepository.registerResponse

    fun onLoginEmailChange(email: String) {
        _loginUiState.value = _loginUiState.value.copy(email = email)
    }

    fun onLoginPasswordChange(password: String) {
        _loginUiState.value =
            _loginUiState.value.copy(password = password)
    }

    fun onRegisterEmailChange(email: String) {
        _registerUiState.value = _registerUiState.value.copy(
            email = email,
        )
    }

    fun onRegisterPasswordChange(password: String) {
        _registerUiState.value = _registerUiState.value.copy(
            password = password,
        )
    }

    fun onRegisterNameChange(name: String) {
        _registerUiState.value = _registerUiState.value.copy(
            name = name,
        )
    }

    fun onRegisterUsernameChange(username: String) {
        _registerUiState.value = _registerUiState.value.copy(
            username = username,
        )
    }

    fun login() {
        viewModelScope.launch {
            authRepository.login(_loginUiState.value.email, _loginUiState.value.password)
            _loginUiState.value = _loginUiState.value.copy(
                userId = loginResponse.value?.data?.userId ?: "",
                username = loginResponse.value?.data?.username ?: "",
                name = loginResponse.value?.data?.name ?: "",
                loginSuccess = loginResponse.value?.success ?: false,
                message = loginResponse.value?.message ?: ""
            )
        }
    }

    fun register() {
        viewModelScope.launch {
            authRepository.register(
                _registerUiState.value.email,
                _registerUiState.value.password,
                _registerUiState.value.name,
                _registerUiState.value.username
            )
            _registerUiState.value = _registerUiState.value.copy(
                registerSuccess = registerResponse.value?.success ?: false,
                message = registerResponse.value?.message ?: "",
                userId = registerResponse.value?.data?.userId.toString() ?: ""
            )
        }
    }
}

data class LoginUiState(
    val userId: String = "",
    val username: String = "",
    val email: String = "",
    val name: String = "",
    val password: String = "",
    var loginSuccess: Boolean = false,
    var message: String = ""
)

data class RegisterUiState(
    val userId: String = "",
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    var registerSuccess: Boolean = false,
    var message: String = ""
)