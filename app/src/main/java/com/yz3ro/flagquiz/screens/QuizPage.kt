package com.yz3ro.flagquiz.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.yz3ro.flagquiz.R
import com.yz3ro.flagquiz.viewmodels.QuizViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun QuizPage(navController: NavController) {
    val viewModel: QuizViewModel = hiltViewModel()
    val randomCountry by viewModel.randomCountry.observeAsState()
    val randomCountryNames by viewModel.randomCountryNames.observeAsState(emptyList())
    var score by remember { mutableStateOf(0) }
    var buttonsEnabled by remember { mutableStateOf(true) }
    val appColor = colorResource(id = R.color.app_color)
    var buttonColors = remember { mutableStateListOf(appColor, appColor, appColor, appColor) }
    val scope = rememberCoroutineScope()
    val showDialog = remember { mutableStateOf(false) }
    var buttonNames by remember { mutableStateOf(listOf<String>()) }

    LaunchedEffect(key1 = true) {
        viewModel.getRandomCountry()
    }

    randomCountry?.let { country ->
        val selectedFlagName = country.flag_name
        val flagUrl = country.flag_url

        LaunchedEffect(key1 = selectedFlagName) {
            viewModel.getRandomCountryName(selectedFlagName)
        }

        LaunchedEffect(key1 = randomCountryNames) {
            if (randomCountryNames.isNotEmpty()) {
                val names = randomCountryNames.map { it.flag_name }
                buttonNames = (names + selectedFlagName).shuffled()
                buttonColors = mutableStateListOf(appColor, appColor, appColor, appColor)
                buttonsEnabled = true
            }
        }

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
                    color = colorResource(id = R.color.app_color)
                )
                Button(
                    onClick = {
                        // show max score and current score
                    },
                    modifier = Modifier.size(90.dp, 40.dp),
                    enabled = true,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.app_color)
                    )
                ) {
                    Text(text = score.toString())
                }
            }
            AsyncImage(
                model = flagUrl,
                contentDescription = "",
                modifier = Modifier.size(350.dp)
            )

            buttonNames.forEachIndexed { index, name ->
                Button(
                    onClick = {
                        if (name == selectedFlagName) {
                            buttonsEnabled = false
                            score += 10
                            scope.launch {
                                delay(1000)
                                viewModel.getRandomCountry()
                            }
                        } else {
                            buttonColors[index] = Color.Red
                            score -= 5
                            scope.launch {
                                delay(1000)
                                buttonColors[index] = appColor
                            }
                        }
                        if(score < 0){
                            showDialog.value = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp, horizontal = 40.dp)
                        .size(0.dp, 60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColors[index]
                    ),
                    enabled = buttonsEnabled
                ) {
                    Text(text = name)
                }
            }
            if (showDialog.value) {
               AlertDialog(
                   onDismissRequest = { showDialog.value },
                   title = { Text(text = "Kaybettin!") },
                   text = { Text(text = " Tekrar oynamak ister misin ?") },
                   confirmButton = {
                       Button(
                           onClick = {
                               score = 0
                               viewModel.getRandomCountry()
                               showDialog.value = false
                           },
                           colors = ButtonDefaults.buttonColors(
                               containerColor = colorResource(id = R.color.app_color)
                           )
                       ) {
                           Text(text = "Evet")
                       }
                   },
                   dismissButton = {
                       Button(
                           onClick = {
                               navController.navigate("feed")
                           },
                           colors = ButtonDefaults.buttonColors(
                               containerColor = colorResource(id = R.color.app_color)
                           )
                       ) {
                           Text(text = "Hayır")
                       }
                   }
               )
            }
        }
    }
}
