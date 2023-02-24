package com.anthonychaufrias.test1.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.anthonychaufrias.test1.ui.screens.signup.SignUpTheme
import com.anthonychaufrias.test1.ui.screens.signup.SingUpForm
import com.anthonychaufrias.test1.ui.theme.Test1Theme
import com.anthonychaufrias.test1.ui.viewmodel.SignUpViewModel

class SignUpFormActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test1Theme {
                SignUpTheme { SingUpForm(SignUpViewModel()) }
            }
        }
    }
}