package com.example.weatherapp.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RowScope.WeatherDetailItem(iconId: Int, label: String, value: String) {
    Box(
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0x33FFFFFF))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = label, fontSize = 12.sp, color = Color.LightGray)
            Text(
                text = value,
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}