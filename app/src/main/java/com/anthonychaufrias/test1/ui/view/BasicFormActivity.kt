package com.anthonychaufrias.test1.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anthonychaufrias.test1.ui.theme.Test1Theme

class BasicFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BasicForm()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BasicForm(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Name")
        TextField(value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth())
        Spacer(modifier = Modifier.height(15.dp))

        Text("Email")
        TextField(value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth())
        Spacer(modifier = Modifier.height(15.dp))

        Text("Country")
        Dropdown()
        Spacer(modifier = Modifier.height(15.dp))

        CheckBox()
        Spacer(modifier = Modifier.height(15.dp))

        OutField()

        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()) {
            Text("Save")
        }

    }
}

@Composable
fun OutField(){
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        value = "textShopName",
        onValueChange = {  },
        label = { Text("Shop Name") },
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 20.dp)
            .fillMaxWidth(),

        )

}

// https://semicolonspace.com/dropdown-menu-jetpack-compose/
// https://www.youtube.com/watch?v=_lee9vN1FiE
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Dropdown() {
    val contextForToast = LocalContext.current.applicationContext
    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")

    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    // Para llamar a este composable es necesario actualizar las versiones de kotlin y compose
    // kotlin_version = '1.7.0'
    // compose_version = '1.1.0'
    // id 'org.jetbrains.kotlin.android' version '1.6.10' apply false
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        // text field
        TextField(
            value = selectedItem,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "Country") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    selectedItem = selectedOption
                    Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}

// https://www.youtube.com/watch?v=Iu074iSpkdE
// https://www.youtube.com/watch?v=EJ9GZ47KXFw
@Composable
fun CheckBox(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start

    ) {
        Checkbox(
            checked = false,
            onCheckedChange = { true },
            modifier = Modifier
                .padding(0.dp)
        )
        Text(
            text = "I agree to Terms & Conditions",
            fontSize = 18.sp
        )
    }
}


@Composable
fun DropdownDemo() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("A", "B", "C", "D", "E", "F")
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(items[selectedIndex],modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { expanded = true })
            .background(
                Color.Gray
            ))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.Red
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    val disabledText = if (s == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = s + disabledText)
                }
            }
        }
    }
}

@Composable
fun MyUI() {
    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")
    val disabledItem = 1
    val contextForToast = LocalContext.current.applicationContext

    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }

    Box(
        contentAlignment = Alignment.Center
    ) {

        // options button
        IconButton(onClick = {
            expanded = true
        }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Open Options"
            )
        }

        // drop down menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            // adding items
            listItems.forEachIndexed { itemIndex, itemValue ->
                DropdownMenuItem(
                    onClick = {
                        Toast.makeText(contextForToast, itemValue, Toast.LENGTH_SHORT)
                            .show()
                        expanded = false
                    },
                    enabled = (itemIndex != disabledItem)
                ) {
                    Text(text = itemValue)
                }
            }
        }
    }
}


// https://www.youtube.com/watch?v=x9bQW8V1WPA
// https://www.youtube.com/watch?v=EmUx8wgRxJw
// https://medium.com/@karthik.dusk/field-validations-using-jetpack-compose-and-mvvm-8c4ea947b35d
// https://stackoverflow.com/questions/66560404/jetpack-compose-unresolved-reference-observeasstate
@Composable
fun TextFieldFor(label: String, marginTop: Dp,
                 type: KeyboardType = KeyboardType.Text){

    /**
     * El detalle es que a cualquier cambio dentro de la UI(controles)
     * el composable vuelve a cargar, entonces
     * si inicializa con vacio de esta forma var value = ""
     * cuando el composable cargue nuevamente, los controles se muestran con valor vacio
     * es decir, se borra el valor anterior
     */
    //var value = ""
    /**
     * El problema es que vuelve a cargar el composable
     * cuando hay rotacion(u otro cambio de config) del dispositivo y se borra el valor
     */
    //var value by remember { mutableStateOf("") }
    /**
     * Guarda el valor despues de los cambios y rotaciones
     */
    var value by rememberSaveable { mutableStateOf("") }

    Text(
        text = label,
        modifier = Modifier
            .padding(top =  marginTop, bottom = 4.dp)
    )
    TextField(
        value = value,
        onValueChange = { value = it },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = type
        ),
        modifier = Modifier
            .fillMaxWidth()
    )
}
// https://www.youtube.com/watch?v=G4YopUFICrI&list=PLrn69hTK5FBwu7VmWBg76v23atiMqz_pY&index=10