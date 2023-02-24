package com.anthonychaufrias.test1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.anthonychaufrias.test1.ui.theme.Test1Theme

class SignUpFormActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Test1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SingUpForm(SignUpViewModel())
                }
            }
        }
    }


    @Composable
    fun SingUpForm(viewModel: SignUpViewModel){
        val margin = 16.dp
        val fullName: String by viewModel.fullName.observeAsState(initial = "")
        val documentID: String by viewModel.documentID.observeAsState(initial = "")
        val email: String by viewModel.email.observeAsState(initial = "")
        val company: String by viewModel.company.observeAsState(initial = "")
        val isButtonEnabled: Boolean by viewModel.isButtonEnabled.observeAsState(initial = false)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = margin)){

            TextFieldFor(
                label = "Full name",
                marginTop = margin,
                value = fullName,
                onValueChanged = { viewModel.onSignUpValuesChanged(it, documentID, email) }
            )
            TextFieldFor(
                label = "Document ID",
                marginTop = margin,
                value = documentID,
                onValueChanged = { viewModel.onSignUpValuesChanged(fullName, it, email) },
                type = KeyboardType.Number
            )
            TextFieldFor(
                label = "E-mail",
                marginTop = margin,
                value = email,
                onValueChanged = { viewModel.onSignUpValuesChanged(fullName, documentID, it) },
                type = KeyboardType.Email
            )
            TextFieldFor(
                label = "Company",
                marginTop = margin,
                value = company,
                onValueChanged = { viewModel.onCompanyChanged(it) }
            )

            PrimaryButton(
                label = "Save", enable = isButtonEnabled,
                onSaveClick = { viewModel.onSignUp() }
            )

        }
    }


    @Composable
    fun PrimaryButton(label: String, enable: Boolean, onSaveClick: () -> Unit) {
        Button(
            onClick = { onSaveClick() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFF4303),
                disabledBackgroundColor = Color(0xFFF78058),
                contentColor = Color.White,
                disabledContentColor = Color.White
            ), enabled = enable
        ) {
            Text(text = label)
        }
    }


    @Composable
    fun TextFieldFor(label: String, marginTop: Dp,
                     value: String, onValueChanged: (String) -> Unit,
                     type: KeyboardType = KeyboardType.Text){

        //var value by rememberSaveable { mutableStateOf("") }

        Text(
            text = label,
            modifier = Modifier
                .padding(top =  marginTop, bottom = 4.dp)
        )
        TextField(
            value = value,
            onValueChange = { onValueChanged(it) },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = type
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }

}