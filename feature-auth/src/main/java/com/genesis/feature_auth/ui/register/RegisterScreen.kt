package com.genesis.feature_auth.ui.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.genesis.core.ui.components.ProgressDialog
import com.genesis.feature_auth.viewmodel.AuthViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = koinViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val registerState by viewModel.registerState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            RegisterForm(
                modifier = Modifier.padding(innerPadding),
                onRegister = { username, email, phoneNumber, password ->
                    viewModel.register(username, email, phoneNumber, password)
                },
                onLoginClick = {
                    navController.navigate("login")
                }
            )
        }
    }

    ProgressDialog(isLoading = isLoading)

    LaunchedEffect(registerState) {
        registerState?.let {
            if (it.isSuccess) {
                scope.launch {
                    snackbarHostState.showSnackbar("Registration successful")
                }
                navController.navigate("login") {
                    popUpTo("register") { inclusive = true }
                }
            } else {
                snackbarHostState.showSnackbar(it.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
}