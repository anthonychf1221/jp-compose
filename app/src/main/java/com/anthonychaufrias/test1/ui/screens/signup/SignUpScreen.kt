package com.anthonychaufrias.test1.ui.screens.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import com.anthonychaufrias.test1.R
import com.anthonychaufrias.test1.data.model.CountryModel
import com.anthonychaufrias.test1.ui.theme.Purple200
import com.anthonychaufrias.test1.ui.theme.Purple500
import com.anthonychaufrias.test1.ui.viewmodel.CountryViewModel
import com.anthonychaufrias.test1.ui.viewmodel.SignUpViewModel

@Composable
fun SingUpForm (
     signUpViewModel: SignUpViewModel,
     countryViewModel: CountryViewModel
){
    val margin = dimensionResource(R.dimen.body_margin)
    val fullName: String by signUpViewModel.fullName.observeAsState(initial = "")
    val documentID: String by signUpViewModel.documentID.observeAsState(initial = "")
    val email: String by signUpViewModel.email.observeAsState(initial = "")
    val company: String by signUpViewModel.company.observeAsState(initial = "")
    val isButtonEnabled: Boolean by signUpViewModel.isButtonEnabled.observeAsState(initial = false)

    val isCountryListLoading: Boolean by countryViewModel.isLoading.observeAsState(initial = true)
    val isDropdownExpanded: Boolean by countryViewModel.isExpanded.observeAsState(initial = false)
    val selectedCountry: CountryModel by countryViewModel.selectedCountry.observeAsState(initial = CountryModel(1, ""))
    val countries: List<CountryModel> by countryViewModel.countries.observeAsState(initial = listOf<CountryModel>())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = margin)){

        TextFieldFor(
            label = stringResource(R.string.full_name_label),
            marginTop = margin,
            value = fullName,
            onValueChanged = { signUpViewModel.onSignUpValuesChanged(it, documentID, email) }
        )
        TextFieldFor(
            label = stringResource(R.string.document_ID_label),
            marginTop = margin,
            value = documentID,
            onValueChanged = { signUpViewModel.onSignUpValuesChanged(fullName, it, email) },
            type = KeyboardType.Number
        )
        TextFieldFor(
            label = stringResource(R.string.email_label),
            marginTop = margin,
            value = email,
            onValueChanged = { signUpViewModel.onSignUpValuesChanged(fullName, documentID, it) },
            type = KeyboardType.Email
        )
        TextFieldFor(
            label = stringResource(R.string.company_label),
            marginTop = margin,
            value = company,
            onValueChanged = { signUpViewModel.onCompanyChanged(it) }
        )

        if( isCountryListLoading ){
            Text(text = stringResource(R.string.loading_message),
                 modifier = Modifier
                    .padding(top =  margin,
                        bottom = dimensionResource(R.dimen.dimen_4dp)))
        }
        else{
            DropdownFor(
                label = stringResource(R.string.country_label),
                marginTop = margin,
                selected = selectedCountry,
                onSelectedChanged = { countryViewModel.onCountryChanged(it) },
                isExpanded = isDropdownExpanded,
                onExpandedChanged = { countryViewModel.onExpandedChanged(it) },
                data = countries
            )
        }

        PrimaryButton(
            label = stringResource(R.string.save_label),
            marginTop = margin,
            enable = isButtonEnabled,
            onSaveClick = { signUpViewModel.onSignUp() }
        )

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownFor(label: String, marginTop: Dp, data: List<CountryModel>,
                selected: CountryModel, onSelectedChanged: (CountryModel) -> Unit,
                isExpanded: Boolean, onExpandedChanged: (Boolean) -> Unit) {

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { onExpandedChanged(!isExpanded) }
    ) {
        TextField(
            value = selected.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = marginTop),
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isExpanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { onExpandedChanged(false) }
        ) {
            data.forEach { selectedOption ->
                DropdownMenuItem(onClick = {
                    onSelectedChanged (selectedOption)
                }) {
                    Text(text = selectedOption.name)
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
            .padding(top = marginTop),
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
