package com.example.cleanarch.feature.splash.presentation.ui

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cleanarch.R
import com.example.cleanarch.core.navigation.NavigationItem
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavController)= Box(Modifier.fillMaxSize()) {
    val scale = remember {
        Animatable(0.0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )
        delay(3000)
        navController.navigate(NavigationItem.Login.route) {
            popUpTo(NavigationItem.Splash.route) {
                inclusive = true
            }
        }
    }

    Image(
        modifier = Modifier
            .align(Alignment.Center)
            .width(200.dp)
            .height(200.dp),
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "",
        alignment = Alignment.Center,

        )

    Text(
        text = "Version",
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(16.dp)
    )
}