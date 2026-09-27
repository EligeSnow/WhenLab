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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whenlab.ui.theme.WhenLabTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Включаем отображение во весь экран (edge-to-edge)
        enableEdgeToEdge()
        // Устанавливаем интерфейс на Compose
        setContent {
            WhenLabTheme {
                // Scaffold создает базовую структуру экрана с поддержкой системных отступов
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Вызываем наш главный Composable-экран
                    Variant7Screen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Главный Composable-компонент Вариант 7
@Composable
fun Variant7Screen(modifier: Modifier = Modifier) {
    // Храним текст, введенный пользователем в поле ввода
    var inputText by remember { mutableStateOf("") }
    
    // Храним результат проверки или текст ошибки
    var resultText by remember { mutableStateOf("") }
    
    // true - если введен некорректный символ, false - если всё верно
    var isError by remember { mutableStateOf(false) }

    // Главная Column - выравнивает содержимое по центру экрана
    Column(
        modifier = modifier
            .fillMaxSize() // Занимаем всю доступную ширину и высоту
            .padding(16.dp), // Внешний отступ от краев экрана
        horizontalAlignment = Alignment.CenterHorizontally, // Выравнивание по горизонтали по центру
        verticalArrangement = Arrangement.Center // Выравнивание по вертикали по центру
    ) {
        // Карточка
        Card(
            modifier = Modifier.fillMaxWidth(), // Карточка растягивается по ширине
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp) // Тень карточки
        ) {
            //  Column для элементов внутри карточки
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), //  отступы внутри карточки
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Лабораторная работа №1",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary
                )


                Text(
                    text = "Вариант 7: Проверка символа",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                // Отступ по вертикали между заголовком и полем ввода
                Spacer(modifier = Modifier.height(20.dp))

                // Поле ввода символа
                OutlinedTextField(
                    value = inputText, // Значение в поле связано с переменной inputText
                    onValueChange = { newValue ->
                        // При каждом изменении текста обновляем inputText
                        inputText = newValue
                        // Сбрасываем результат и ошибку при вводе нового символа
                        resultText = ""
                        isError = false
                    },
                    label = { Text("Введите символ") }, // Подпись над полем ввода
                    isError = isError, // Если true, рамка подсвечивается красным
                    singleLine = true // Ограничиваем ввод одной строкой
                )

                // Отступ между полем ввода и кнопками
                Spacer(modifier = Modifier.height(16.dp))

                // Row (строка) - располагает кнопки горизонтально рядом друг с другом
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp), // Расстояние между кнопками
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Кнопка для запуска проверки
                    Button(
                        onClick = {
                            // 1. Проверяем с помощью условия if:
                            // - Длина строки должна быть ровно 1 символ
                            // - Символ должен быть заглавной латинской буквой от 'A' до 'Z'
                            if (inputText.length != 1 || inputText[0] !in 'A'..'Z') {
                                isError = true
                                resultText = "Ошибка: введенный символ не является латинской прописной буквой!"
                            } else {
                                // 2. Если условие выполнено, анализируем символ через оператор when
                                isError = false
                                val char = inputText[0]
                                resultText = when (char) {
                                    // Если это одна из букв L, M, K, D
                                    'L', 'M', 'K', 'D' -> "Это согласные буквы"
                                    // Для остальных заглавных латинских букв
                                    else -> "Возможно, это гласные буквы"
                                }
                            }
                        }
                    ) {
                        Text("Проверить")
                    }

                    // Кнопка для сброса введенных данных
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

                // Вывод результата проверки (отображается, только если resultText не пустой)
                if (resultText.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = resultText,
                        // Если ошибка - выводим красным цветом, иначе - темно-зеленым
                        color = if (isError) Color.Red else Color(0xFF2E7D32),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

// Предпросмотр интерфейса
@Preview(showBackground = true)
@Composable
fun Variant7ScreenPreview() {
    WhenLabTheme {
        Variant7Screen()
    }
}