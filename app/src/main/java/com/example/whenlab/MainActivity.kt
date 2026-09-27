package com.example.whenlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whenlab.ui.theme.WhenLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhenLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Variant7Screen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Variant7Screen(modifier: Modifier = Modifier) {
    var inputText by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Вариант 7: Проверка символа",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputText,
            onValueChange = {
                inputText = it
                resultText = ""
                isError = false
            },
            label = { Text("Введите символ") },
            isError = isError,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if (inputText.length != 1 || inputText[0] !in 'A'..'Z') {
                        isError = true
                        resultText = "Ошибка: введенный символ не является латинской прописной буквой!"
                    } else {
                        isError = false
                        val char = inputText[0]
                        resultText = when (char) {
                            'L', 'M', 'K', 'D' -> "Это согласные буквы"
                            else -> "Возможно, это гласные буквы"
                        }
                    }
                }
            ) {
                Text("Проверить")
            }

            OutlinedButton(
                onClick = {
                    inputText = ""
                    resultText = ""
                    isError = false
                }
            ) {
                Text("Очистить")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (resultText.isNotEmpty()) {
            Text(
                text = resultText,
                color = if (isError) Color.Red else Color(0xFF2E7D32),
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Variant7ScreenPreview() {
    WhenLabTheme {
        Variant7Screen()
    }
}