package argha.netutils.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import argha.netutils.viewmodels.AuthViewModel

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    viewModel: AuthViewModel
) {

    LoginScreen(viewModel = viewModel) { userId, email, username, name ->

    }

    RegisterScreen(viewModel = viewModel) { userId ->

    }

}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    onLoginSuccess: (userId: String, email: String, username: String, name: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.loginUiState.value.email,
            onValueChange = { viewModel.onLoginEmailChange(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.loginUiState.value.password,
            onValueChange = { viewModel.onLoginPasswordChange(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.login() },
            modifier = Modifier.fillMaxWidth(),
            enabled = true
        ) {
            Text("Login")
        }

        if (viewModel.loginUiState.value.loginSuccess) {
            Text(text = viewModel.loginUiState.value.message)
            onLoginSuccess(
                viewModel.loginUiState.value.userId,
                viewModel.loginUiState.value.email,
                viewModel.loginUiState.value.username,
                viewModel.loginUiState.value.name
            )
        } else if (!viewModel.loginUiState.value.loginSuccess) {
            Text(text = viewModel.loginUiState.value.message)
        } else {
            Text(text = "Something went wrong")
        }
    }
}

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    onRegisterSuccess: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.registerUiState.value.name,
            onValueChange = { viewModel.onRegisterNameChange(it) },
            label = { Text("Name") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.registerUiState.value.email,
            onValueChange = { viewModel.onRegisterEmailChange(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.registerUiState.value.username,
            onValueChange = { viewModel.onRegisterUsernameChange(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.registerUiState.value.password,
            onValueChange = { viewModel.onRegisterPasswordChange(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.register() },
            modifier = Modifier.fillMaxWidth(),
            enabled = true
        ) {
            Text("Register")
        }

        if (viewModel.registerUiState.value.registerSuccess) {
            Text(text = viewModel.registerUiState.value.message)
            onRegisterSuccess(
                viewModel.registerUiState.value.userId,
            )
        } else if (!viewModel.registerUiState.value.registerSuccess) {
            Text(text = viewModel.registerUiState.value.message)
        } else {
            Text(text = "Something went wrong")
        }
    }
}