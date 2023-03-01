package com.anthonychaufrias.test1.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anthonychaufrias.test1.ui.theme.Test1Theme

@Composable
fun AppTheme(content: @Composable () -> Unit){
    Test1Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}