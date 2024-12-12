package com.example.nooruapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nooruapp.data.Current
import com.example.nooruapp.data.WeatherData

@Composable
fun ConditionsBar(modifier: Modifier, data: WeatherData) {
    Box(modifier = modifier.fillMaxWidth().padding(top = 20.dp), contentAlignment = Alignment.Center){
        Row(modifier = modifier
            .background(color = Color(0xFFF2F2F2), shape = RoundedCornerShape(10.dp))
            .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Humidity", color = Color(0xFFC4C4C4), fontSize = 12.sp)
                Text(text = "${data.current.humidity}%", color = Color(0xFF9A9A9A))
            }
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally)  {
                Text(text= "UV", color = Color(0xFFC4C4C4), fontSize = 12.sp)
                Text(text= "${data.current.uv?.toInt()}", color = Color(0xFF9A9A9A))
            }
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally)  {
                Text(text= "Feels Like", color = Color(0xFFC4C4C4), fontSize = 8.sp)
                Text(text= "${data.current.feelslike_f?.toInt()}°", color = Color(0xFF9A9A9A))
            }
        }
    }
}