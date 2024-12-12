package com.example.nooruapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.defaultMinSize
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nooruapp.R
import com.example.nooruapp.data.Current
import com.example.nooruapp.data.WeatherData
import com.example.nooruapp.util.IconResizer

@Composable
fun WeatherCard(
    modifier: Modifier,
    data: WeatherData?
){

    if(data == null){
        EmptyCityWeatherCard(modifier = modifier)
    }else {
        CityWeatherCard(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp), data!!)
        ConditionsBar(modifier = Modifier.padding(start = 10.dp, end = 10.dp), data)
    }
}

@Composable
fun EmptyCityWeatherCard(modifier: Modifier){
    Box(
        modifier
            .fillMaxWidth()) {
        Column(modifier = modifier.align(Alignment.Center)) {
            Row {
                Text(text = "No City Selected", fontSize = 35.sp, fontWeight = FontWeight.Bold)
            }
            Row(modifier = modifier.align(Alignment.CenterHorizontally)) {
                Text(text = "Please Search For A City", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun CityWeatherCard(modifier: Modifier, data: WeatherData){
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ){
        println("condition: "+data.current.condition?.code)
        Column(modifier = Modifier.align(Alignment.TopCenter), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = modifier){
                AsyncImage(
                    modifier = Modifier.size(128.dp),//really small unless I for 128
                    model = "https:${IconResizer.resizeIcon(data.current.condition?.icon!!)}",
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.sunny),//for lack of better alternatives
                    fallback = painterResource(id = R.drawable.sunny)//for lack of better alternatives
                )
            }
            Row(modifier = modifier){
                Text(text = data.location.name, fontSize = 30.sp)
            }
            //the degree symbol is screwing with with our alignment, we really want
            //to align to the center of the numbers, but the degree symbol makes
            //throws that off, so for simplicity sake, I just offset this row by 10dp
            Row(modifier = modifier.absoluteOffset(x = 10.dp)){
                Text(text = "${data.current.temp_f?.toInt()}°", fontSize = 70.sp)
            }
        }
    }
}