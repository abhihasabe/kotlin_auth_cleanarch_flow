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
import androidx.compose.ui.platform.LocalConfiguration
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
import com.example.cleanarch.feature.auth.data.model.RegisterRequest
import com.example.cleanarch.feature.auth.presenation.state.LoginState
import com.example.cleanarch.feature.auth.presenation.state.RegisterState
import com.example.cleanarch.feature.auth.presenation.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Register(navController: NavController) {

    val context = LocalContext.current
    val authViewModel = hiltViewModel<AuthViewModel>()
    var isLoading by remember { mutableStateOf(false) }


    LaunchedEffect(key1 = true) {
        authViewModel.registerStateFlow.collectLatest {
            when (it) {
                is RegisterState.Success -> {
                    Toast.makeText(context, "Register Successfully", Toast.LENGTH_SHORT).show()
                    navController.navigate(NavigationItem.Home.route) {
                        popUpTo(NavigationItem.Login.route) {
                            inclusive = true
                        }
                    }
                }

                is RegisterState.Error -> {
                    isLoading = false
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is RegisterState.Loading -> {
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
        var firstName by rememberSaveable { mutableStateOf("") }
        var lastName by rememberSaveable { mutableStateOf("") }
        var gender by rememberSaveable { mutableStateOf("") }
        var email by rememberSaveable { mutableStateOf("") }
        var mobileNumber by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var confirmPassword by rememberSaveable { mutableStateOf("") }


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
                .fillMaxWidth(),
            text = "Signup Now",
            style = TextStyle(
                textAlign = TextAlign.Center, fontSize = 18.sp, fontWeight = FontWeight.Bold
            )
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            text = "Please fill the details and create account",
            style = TextStyle(textAlign = TextAlign.Center, fontSize = 8.sp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                modifier = Modifier.width(width = LocalConfiguration.current.screenWidthDp.dp / 3),
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name", style = TextStyle(fontSize = 12.sp)) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                shape = RoundedCornerShape(4.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedTextField(
                modifier = Modifier.width(width = LocalConfiguration.current.screenWidthDp.dp / 2),
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name", style = TextStyle(fontSize = 12.sp)) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                shape = RoundedCornerShape(4.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(59.dp),
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Gender", style = TextStyle(fontSize = 12.sp)) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(4.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(59.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address", style = TextStyle(fontSize = 12.sp)) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(4.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(59.dp),
            value = mobileNumber,
            onValueChange = { mobileNumber = it },
            label = { Text("Mobile Number", style = TextStyle(fontSize = 12.sp)) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(4.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(59.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", style = TextStyle(fontSize = 12.sp)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(4.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(59.dp),
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password", style = TextStyle(fontSize = 12.sp)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(4.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))
        Button(colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF024d94)),
            shape = RoundedCornerShape(10),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (firstName.isEmpty()) {
                    Toast.makeText(context, "Please enter Username", Toast.LENGTH_SHORT).show()
                } else if (email.isEmpty()) {
                    Toast.makeText(context, "Please enter Email", Toast.LENGTH_SHORT).show()
                } else if (password.isEmpty()) {
                    Toast.makeText(context, "Please enter Password", Toast.LENGTH_SHORT).show()
                } else {
                    authViewModel.register(
                        RegisterRequest(
                            firstName, lastName, gender, email, mobileNumber, password
                        )
                    )
                }
            }) {
            if (isLoading) CircularProgressIndicator(
                color = Color.White, modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            ) else {
                Text(text = "SignUp")
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Do you have an account? ",
                style = TextStyle(textAlign = TextAlign.Center, fontSize = 12.sp)
            )
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(NavigationItem.Login.route)
                }, text = "Log In", style = TextStyle(
                    textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 12.sp
                )
            )
        }
    }
}