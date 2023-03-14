package com.anthonychaufrias.test1.ui.screens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anthonychaufrias.test1.R
import com.anthonychaufrias.test1.ui.theme.Purple500
import com.anthonychaufrias.test1.ui.theme.Teal200

@Preview
@Composable
fun AnotherSignUp(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Teal200)
            .padding(dimensionResource(R.dimen.body_margin))
    ) {

        NewLabel(
            label = "First",
            modifier = Modifier.fillMaxWidth()
                .background(Purple500)
        )

        NewLabel(
            label = "Second",
            modifier = Modifier.fillMaxWidth()
                .padding(top = 20.dp)
                .background(Purple500)
        )

    }
}


@Composable
fun NewLabel(label: String, modifier: Modifier){
    Text(
        text = label,
        modifier = modifier
    )
}
