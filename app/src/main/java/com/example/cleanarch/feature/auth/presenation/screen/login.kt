package com.example.cleanarch.feature.auth.presenation.screen

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cleanarch.R
import com.example.cleanarch.core.navigation.NavigationItem
import com.example.cleanarch.core.util.InternetConnection
import com.example.cleanarch.core.util.InternetConnection.isNetworkAvailable
import com.example.cleanarch.feature.auth.data.model.LoginRequest
import com.example.cleanarch.feature.auth.presenation.state.LoginState
import com.example.cleanarch.feature.auth.presenation.viewmodel.AuthViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.annotation.meta.When

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun Login(navController: NavController) = Column(
    Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {
    val context = LocalContext.current
    val authViewModel = hiltViewModel<AuthViewModel>()
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        authViewModel.loginStateFlow.collectLatest {
            when (it) {
                is LoginState.Success -> {
                    Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
                    navController.navigate(NavigationItem.Home.route) {
                        popUpTo(NavigationItem.Login.route) {
                            inclusive = true
                        }
                    }
                }

                is LoginState.Error -> {
                    isLoading = false
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is LoginState.Loading -> {
                    isLoading = it.isLoading
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var userName by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }

        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(80.dp)
                .height(80.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            alignment = Alignment.Center,

            )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .clickable {
                    navController.navigate(NavigationItem.Login.route)
                }, text = "Login Now", style = TextStyle(
                textAlign = TextAlign.Center, fontSize = 18.sp, fontWeight = FontWeight.Bold
            )
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .clickable {
                    navController.navigate(NavigationItem.ForgotPassword.route)
                },
            text = "Please Login to continue using our app",
            style = TextStyle(textAlign = TextAlign.Center, fontSize = 8.sp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            singleLine = true,
            modifier = Modifier
                .height(59.dp)
                .fillMaxWidth(),
            value = userName,
            onValueChange = { userName = it },
            label = { Text("Email", style = TextStyle(fontSize = 12.sp)) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            shape = RoundedCornerShape(4.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(59.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", style = TextStyle(fontSize = 12.sp)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(4.dp)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(NavigationItem.ForgotPassword.route)
                },
            text = "Forgot Password?",
            style = TextStyle(textAlign = TextAlign.End, fontSize = 12.sp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF024d94)),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                GlobalScope.launch {
                    if (userName.isEmpty()) {
                        showToast("Please enter Username", context)
                    } else if (password.isEmpty()) {
                        showToast("Please enter Password", context)
                    } else {
                        authViewModel.login(LoginRequest(userName, password));
                    }
                }
            }) {
            if (isLoading) CircularProgressIndicator(
                color = Color.White, modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            ) else {
                Text(text = "Login")
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Don't have an account? ",
                style = TextStyle(textAlign = TextAlign.Center, fontSize = 12.sp)
            )
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(NavigationItem.Register.route)
                }, text = "Sign Up", style = TextStyle(
                    textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 12.sp
                )
            )
        }
    }
}

fun showToast(title: String, context: Context) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(context, title, Toast.LENGTH_SHORT).show()
    }
}
