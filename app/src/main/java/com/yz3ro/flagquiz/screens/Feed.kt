package com.yz3ro.flagquiz.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yz3ro.flagquiz.R

@Composable
fun Feed(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.flag_game_logo_big),
            contentDescription = "",
            modifier = Modifier.size(300.dp)
        )
        Text(
            text = "FlagQuiz",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(
                id = R.color.app_color
            )
        )
        Button(
            onClick = { navController.navigate("quiz_page") },
            modifier = Modifier.size(250.dp, 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.option)
            )
        ) {
            Text(text = "OYNA")
        }
    }
}