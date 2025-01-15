package com.surivalcoding.composerecipeapp.ui

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.surivalcoding.composerecipeapp.R

object AppTextStyles {
    private val poppinsFamily = FontFamily(
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_regular, FontWeight.Normal),
    )

    val normalTextBold = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = poppinsFamily,
    )

    val smallTextBold = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = poppinsFamily,
    )


    val smallTextRegular = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = poppinsFamily,
    )
}