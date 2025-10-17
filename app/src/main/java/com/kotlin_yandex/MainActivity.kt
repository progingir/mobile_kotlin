package com.kotlin_yandex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kotlin_yandex.ui.theme.Kotlin_yandexTheme
import com.kotlin_yandex.ui.components.UserForm

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kotlin_yandexTheme {
                UserForm()
            }
        }
    }
}