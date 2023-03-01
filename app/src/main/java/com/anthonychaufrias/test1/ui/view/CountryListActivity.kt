package com.anthonychaufrias.test1.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.anthonychaufrias.test1.ui.screens.country.CountryList
import com.anthonychaufrias.test1.ui.screens.AppTheme
import com.anthonychaufrias.test1.ui.theme.Test1Theme
import com.anthonychaufrias.test1.ui.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryListActivity: ComponentActivity() {
    private val countryViewModel: CountryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test1Theme {
                AppTheme { CountryList(
                    countryViewModel
                ) }
            }
        }
    }
}