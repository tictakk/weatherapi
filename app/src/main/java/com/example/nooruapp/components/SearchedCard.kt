package com.example.nooruapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nooruapp.R
import com.example.nooruapp.data.WeatherData
import com.example.nooruapp.util.IconResizer

@Composable
fun SearchedCard(
    modifier: Modifier,
    onClicked: () -> Unit,
    data: WeatherData?
) {
    if(data != null) {
        Column(
            modifier = Modifier.fillMaxWidth().clickable(onClick = onClicked),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .background(color = Color(0xFFF2F2F2), shape = RoundedCornerShape(10.dp))
                    .width(360.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.Start) {
                    Text("${data.location.name}", fontSize = 20.sp)
                    Text("${data.current.temp_f?.toInt()}°", fontSize = 70.sp)
                }
                Column(modifier = Modifier.padding(12.dp)) {
                    AsyncImage(
                        modifier = Modifier.size(128.dp),
                        model = "https:${IconResizer.resizeIcon(data.current.condition?.icon!!)}",
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.sunny),//for lack of better alternatives
                        fallback = painterResource(id = R.drawable.sunny)//for lack of better alternatives
                    )
                }
            }
        }
    }
}