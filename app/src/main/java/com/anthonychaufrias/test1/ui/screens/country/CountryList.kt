package com.anthonychaufrias.test1.ui.screens.country

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.anthonychaufrias.test1.R
import com.anthonychaufrias.test1.data.model.CountryModel
import com.anthonychaufrias.test1.ui.viewmodel.CountryViewModel

@Composable
fun CountryList (
    countryViewModel: CountryViewModel
){
    val margin = dimensionResource(R.dimen.body_margin)

    val isCountryListLoading: Boolean by countryViewModel.isLoading.observeAsState(initial = true)
    val countries: List<CountryModel> by countryViewModel.countries.observeAsState(initial = listOf<CountryModel>())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = margin)){

        if( isCountryListLoading ){
            Text(text = stringResource(R.string.loading_message),
                modifier = Modifier
                    .padding(top =  margin,
                        bottom = dimensionResource(R.dimen.dimen_4dp)))
        }
        else {
            LazyColumn {
                items(countries) { country ->
                    CountryDetail(country)
                }
            }
        }
    }
}

@Composable
fun CountryDetail(country: CountryModel){
    Text(text = country.name)
}
