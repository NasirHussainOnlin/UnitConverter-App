package com.example.unitconverter


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                themain()
                }
            }
        }
    }
}

@Composable
fun themain(){
    UnitConverter()
}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor by remember { mutableStateOf(1.00) }
    var oconversionFactor by remember { mutableStateOf(1.00) }

//    Convertion Function
fun convertUnits() {
    val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
    val result = inputValueDouble * conversionFactor / oconversionFactor
    outputValue = "%.4f".format(result)
}



    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text(text = "Unit Converter",
            style = TextStyle(
//                fontFamily = Fontfamily(),
                color = Color.Gray,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(value = inputValue,
            onValueChange = {inputValue = it
                            convertUnits()
                          },
            label = { Text(text = "Enter Value")})
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row {
       Box {
//           Input Button
        Button(onClick = { iExpanded = true }) {
            Text(text = "$inputUnit")
            Icon(Icons.Default.ArrowDropDown,contentDescription="Click the Arrow for Options!")
        }
           DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
               DropdownMenuItem(
                   text = { Text(text = "Centimeters")},
                   onClick = { iExpanded = false
                   inputUnit = "Centimeters"
                   conversionFactor = 0.01
                   convertUnits()}

               )
               DropdownMenuItem(
                   text = { Text(text = "Meters")},
                   onClick = { iExpanded = false
                   inputUnit = "Meters"
                   conversionFactor = 1.0
                   convertUnits()}
               )
               DropdownMenuItem(
                   text = { Text(text = "Feet")},
                   onClick = { iExpanded = false
                   inputUnit = "Feet"
                   conversionFactor = 0.3048
                   convertUnits()}
               )
               DropdownMenuItem(
                   text = { Text(text = "Millimeters")},
                   onClick = { iExpanded = false
                   inputUnit = "Millimeters"
                   conversionFactor = 0.001
                   convertUnits()}
               )
           }

       }
//            space aside buttons.
            Spacer(modifier = Modifier.width(16.dp))

//      Output Button
        Box {
        Button(onClick = { oExpanded = true}) {
            Text(text = "$outputUnit")
            Icon(Icons.Default.ArrowDropDown,contentDescription="")
        }
            DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                DropdownMenuItem(
                    text = { Text(text = "Centimeters")},
                    onClick = { oExpanded = false
                    outputUnit = "Centimeters"
                    oconversionFactor = 0.01
                    convertUnits()}
                )
                DropdownMenuItem(
                    text = { Text(text = "Meters")},
                    onClick = { oExpanded = false
                    outputUnit = "Meters"
                    oconversionFactor = 1.00
                    convertUnits()}
                )
                DropdownMenuItem(
                    text = { Text(text = "Feet")},
                    onClick = { oExpanded = false
                    outputUnit = "Feet"
                    oconversionFactor = 0.3048
                    convertUnits()}
                )
                DropdownMenuItem(
                    text = { Text(text = "Millimeters")},
                    onClick = { oExpanded = false
                    outputUnit = "Millimeters"
                    oconversionFactor = 0.001
                    convertUnits()})
            }
        }
        //
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $outputValue $outputUnit",
            style = TextStyle(
                color = Color.Magenta,
                fontSize = 20 .sp,
                fontWeight = FontWeight.Black
                )
        )
    }
}



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}