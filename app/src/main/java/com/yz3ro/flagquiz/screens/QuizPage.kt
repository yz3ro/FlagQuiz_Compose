package com.yz3ro.flagquiz.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.yz3ro.flagquiz.QuizViewModel
import com.yz3ro.flagquiz.R
import timber.log.Timber

@Composable
fun QuizPage() {
    val viewModel: QuizViewModel = hiltViewModel()
    val randomCountry = viewModel.randomCountry.observeAsState()
    val randomCountryNames = viewModel.randomCountryName.observeAsState()
    LaunchedEffect(key1 = true) {
        viewModel.getRandomCountry()
        viewModel.getRandomCountryName(randomCountry.value?.flag_name ?: "")
    }
    randomCountry.let { country ->
        Timber.d("Random country: ${country.value?.flag_name}")
        val selectedFlagName = country.value?.flag_name
        val flagUrl = country.value?.flag_url
        Timber.d("Random country: ${country.value?.flag_url}")
        Timber.d("Random country: ${country.value?.flag_region}")

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "FlagQuiz",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(
                        id = R.color.app_color
                    )
                )
                Button(
                    onClick = {
                        // add show max score and current score

                    }, modifier = Modifier.size(90.dp, 40.dp),
                    enabled = true,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.app_color)
                    )
                ) {
                    Text(text = "0")
                }
            }
            AsyncImage(model = flagUrl, contentDescription = "", modifier = Modifier.size(350.dp))

            // add random flag names
        }
    }
}