package com.kotlin_yandex.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.kotlin_yandex.R
import com.kotlin_yandex.ui.theme.Kotlin_yandexTheme

@Composable
fun UserForm() {

    var name by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf(25f) }
    var gender by rememberSaveable { mutableStateOf("") }
    var isSubscribed by rememberSaveable { mutableStateOf(false) }
    var summary by rememberSaveable { mutableStateOf("") }

    val genderMaleLabel = stringResource(R.string.gender_male)
    val genderFemaleLabel = stringResource(R.string.gender_female)

    LaunchedEffect(Unit) {
        if (gender.isBlank()) {
            gender = genderMaleLabel
        }
    }

    val subYes = stringResource(R.string.subscription_yes)
    val subNo = stringResource(R.string.subscription_no)
    val summaryTemplate = stringResource(R.string.summary_result)

    val isNameValid = name.isNotBlank()
    val isButtonEnabled = isNameValid


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            imageVector = Icons.Filled.Person,
            contentDescription = stringResource(R.string.cd_default_avatar),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .padding(top = 50.dp)
        )

        Text(
            text = stringResource(R.string.title_user_form),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 40.dp, bottom = 30.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.hint_name)) },
            isError = !isNameValid,

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            supportingText = {
                if (!isNameValid) {
                    Text(stringResource(R.string.error_name_empty))
                }
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(R.string.label_age, age.toInt()),
                modifier = Modifier.width(100.dp)
            )
            Slider(
                value = age,
                onValueChange = { age = it },
                valueRange = 1f..100f,
                steps = 98,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.label_gender), modifier = Modifier.padding(end = 16.dp))

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                RadioButton(
                    selected = gender == genderMaleLabel,
                    onClick = { gender = genderMaleLabel }
                )
                Text(genderMaleLabel)
            }

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                RadioButton(
                    selected = gender == genderFemaleLabel,
                    onClick = { gender = genderFemaleLabel }
                )
                Text(genderFemaleLabel)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isSubscribed,
                onCheckedChange = { isSubscribed = it }
            )
            Text(stringResource(R.string.label_subscribe))
        }


        Button(
            onClick = {

                val subStatus = if (isSubscribed) {
                    subYes
                } else {
                    subNo
                }

                summary = String.format(
                    summaryTemplate,
                    name,
                    age.toInt(),
                    gender,
                    subStatus
                )
            },
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.button_send))
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (summary.isNotEmpty()) {
            Text(
                text = summary,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
        }
    }
}

@Preview(name = "Light Mode - Portrait")
@Composable
fun UserFormPreviewLight() {
    Kotlin_yandexTheme(darkTheme = false) {
        Surface {
            UserForm()
        }
    }
}

@Preview(name = "Dark Mode - Landscape")
@Composable
fun UserFormPreviewDarkLandscape() {
    Kotlin_yandexTheme(darkTheme = true) {
        Surface {
            UserForm()
        }
    }
}