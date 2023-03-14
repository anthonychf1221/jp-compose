package com.anthonychaufrias.test1.ui.screens.signup

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.anthonychaufrias.test1.R
import com.anthonychaufrias.test1.data.model.CountryModel
import com.anthonychaufrias.test1.domain.model.SignUp
import com.anthonychaufrias.test1.ui.theme.Purple200
import com.anthonychaufrias.test1.ui.theme.Purple500
import com.anthonychaufrias.test1.ui.viewmodel.CountryViewModel
import com.anthonychaufrias.test1.ui.viewmodel.SignUpViewModel
import com.anthonychaufrias.test1.util.UIText
import com.anthonychaufrias.test1.util.asString

@Composable
fun SingUpForm (
     signUpViewModel: SignUpViewModel,
     countryViewModel: CountryViewModel
){
    val margin = dimensionResource(R.dimen.among_controls_margin)

    val signUp: SignUp by signUpViewModel.signUpModel.observeAsState(
        initial = SignUp.emptyObject()
    )

    val result: UIText? by signUpViewModel.result.observeAsState(initial = null)
    val isButtonEnabled: Boolean by signUpViewModel.isButtonEnabled.observeAsState(initial = false)

    val isCountryListLoading: Boolean by countryViewModel.isLoading.observeAsState(initial = true)
    val isDropdownExpanded: Boolean by countryViewModel.isExpanded.observeAsState(initial = false)
    val selectedCountry: CountryModel by countryViewModel.selectedCountry.observeAsState(initial = CountryModel(1, ""))
    val countries: List<CountryModel> by countryViewModel.countries.observeAsState(initial = listOf<CountryModel>())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.body_margin))){

        FormOutLinedTextField(
            label = stringResource(R.string.full_name_label),
            modifier = Modifier.fillMaxWidth(),
            value = signUp.fullName,
            onValueChanged = { fullName ->
                signUpViewModel.onSignUpValuesChanged(
                    fullName, signUp.documentID, signUp.email,
                    signUp.company, selectedCountry.id
                )}
        )
        FormOutLinedTextField(
            label = stringResource(R.string.document_ID_label),
            modifier = Modifier
                .padding(top =  margin).fillMaxWidth(),
            value = signUp.documentID,
            onValueChanged = { documentID ->
                signUpViewModel.onSignUpValuesChanged(
                    signUp.fullName, documentID, signUp.email,
                    signUp.company, selectedCountry.id
                )},
            type = KeyboardType.Number
        )
        FormOutLinedTextField(
            label = stringResource(R.string.email_label),
            modifier = Modifier
                .padding(top =  margin).fillMaxWidth(),
            value = signUp.email,
            onValueChanged = { email ->
                signUpViewModel.onSignUpValuesChanged(
                    signUp.fullName, signUp.documentID, email,
                    signUp.company, selectedCountry.id
                )},
            type = KeyboardType.Email
        )
        FormOutLinedTextField(
            label = stringResource(R.string.company_label),
            modifier = Modifier
                .padding(top =  margin).fillMaxWidth(),
            value = signUp.company,
            onValueChanged = { company ->
                signUpViewModel.onSignUpValuesChanged(
                    signUp.fullName, signUp.documentID,
                    signUp.email, company, selectedCountry.id
                )}
        )

        if( isCountryListLoading ){
            Text(text = stringResource(R.string.loading_message),
                 modifier = Modifier
                    .padding(top =  margin,
                        bottom = dimensionResource(R.dimen.dimen_4dp)))
        }
        else{
            FormDropdown(
                label = stringResource(R.string.country_label),
                modifier = Modifier
                    .fillMaxWidth().padding(top = margin),
                selected = selectedCountry,
                onSelectedChanged = { country ->
                    countryViewModel.onCountryChanged(country)
                    signUpViewModel.onSignUpValuesChanged(
                        signUp.fullName, signUp.documentID,
                        signUp.email, signUp.company, country.id
                    )
                },
                isExpanded = isDropdownExpanded,
                onExpandedChanged = { countryViewModel.onExpandedChanged(it) },
                data = countries
            )
        }

        PrimaryButton(
            label = stringResource(R.string.save_label),
            modifier = Modifier.fillMaxWidth()
                .height(dimensionResource(R.dimen.button_height))
                .padding(top = margin),
            enable = isButtonEnabled,
            onSaveClick = { signUpViewModel.onSignUp() }
        )

        Result(result)
    }
}

@Composable
fun Result(result: UIText?){
    if(result == null){
        return
    }

    val message = result.asString(LocalContext.current.applicationContext)
    Message(message)

    /*when(result){
        is UIText.ErrorMessageCodeResource -> {
            result.asString(LocalContext.current.applicationContext)
        }
        is UIText.SuccessMessageCodeResource -> {

        }
        else -> {}
    }*/
}

@Composable
fun Message(message: String){
    val contextForToast = LocalContext.current.applicationContext
    Toast.makeText(contextForToast, message, Toast.LENGTH_SHORT).show()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FormDropdown(label: String, data: List<CountryModel>, selected: CountryModel,
                 isExpanded: Boolean, modifier: Modifier,
                 onSelectedChanged: (CountryModel) -> Unit,
                 onExpandedChanged: (Boolean) -> Unit) {

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { onExpandedChanged(!isExpanded) }
    ) {
        OutlinedTextField(
            value = selected.name,
            modifier = modifier,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isExpanded
                )
            },
            // esto hara que se muestre el background de color gris
            //colors = ExposedDropdownMenuDefaults.textFieldColors()
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
fun PrimaryButton(label: String, enable: Boolean,
                  modifier: Modifier, onSaveClick: () -> Unit) {
    Button(
        onClick = { onSaveClick() },
        modifier = modifier,
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
fun FormOutLinedTextField(label: String, value: String,
                          modifier: Modifier, onValueChanged: (String) -> Unit,
                          type: KeyboardType = KeyboardType.Text){
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            keyboardType = type,
            imeAction = ImeAction.Next
        ),
        value = value,
        onValueChange = { onValueChanged(it) },
        maxLines = 1,
        singleLine = true,
        label = { Text(label) },
        modifier = modifier
    )
}

