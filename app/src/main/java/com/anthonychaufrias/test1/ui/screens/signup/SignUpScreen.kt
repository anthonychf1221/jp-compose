package com.anthonychaufrias.test1.ui.screens.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import com.anthonychaufrias.test1.R
import com.anthonychaufrias.test1.ui.theme.Purple200
import com.anthonychaufrias.test1.ui.theme.Purple500
import com.anthonychaufrias.test1.ui.viewmodel.SignUpViewModel

@Composable
fun SingUpForm(viewModel: SignUpViewModel){
    val margin = dimensionResource(R.dimen.body_margin)
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
            label = stringResource(R.string.full_name_label),
            marginTop = margin,
            value = fullName,
            onValueChanged = { viewModel.onSignUpValuesChanged(it, documentID, email) }
        )
        TextFieldFor(
            label = stringResource(R.string.document_ID_label),
            marginTop = margin,
            value = documentID,
            onValueChanged = { viewModel.onSignUpValuesChanged(fullName, it, email) },
            type = KeyboardType.Number
        )
        TextFieldFor(
            label = stringResource(R.string.email_label),
            marginTop = margin,
            value = email,
            onValueChanged = { viewModel.onSignUpValuesChanged(fullName, documentID, it) },
            type = KeyboardType.Email
        )
        TextFieldFor(
            label = stringResource(R.string.company_label),
            marginTop = margin,
            value = company,
            onValueChanged = { viewModel.onCompanyChanged(it) }
        )

        Dropdown(
            label = stringResource(R.string.country_label),
            marginTop = margin
        )

        PrimaryButton(
            label = stringResource(R.string.save_label),
            marginTop = margin,
            enable = isButtonEnabled,
            onSaveClick = { viewModel.onSignUp() }
        )

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Dropdown(label: String, marginTop: Dp) {
    val contextForToast = LocalContext.current.applicationContext
    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")

    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = selectedItem,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top =  marginTop),
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    selectedItem = selectedOption
                    //Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}


@Composable
fun PrimaryButton(label: String, marginTop: Dp,
                  enable: Boolean, onSaveClick: () -> Unit) {
    Button(
        onClick = { onSaveClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.button_height))
            .padding(top =  marginTop),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Purple500,
            disabledBackgroundColor = Purple200,
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
            .padding(top =  marginTop,
                     bottom = dimensionResource(R.dimen.dimen_4dp))
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
