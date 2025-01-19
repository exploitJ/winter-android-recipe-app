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
        Font(R.font.poppins_semibold, FontWeight.SemiBold),
        Font(R.font.poppins_regular)
    )

    // Title Text Styles
    val titleTextBold = TextStyle(
        fontSize = 50.sp,
        lineHeight = 75.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = poppinsFamily
    )

    val titleTextRegular = TextStyle(
        fontSize = 50.sp,
        lineHeight = 75.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = poppinsFamily
    )

    // Header Text Styles
    val headerTextBold = TextStyle(
        fontSize = 30.sp,
        lineHeight = 45.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = poppinsFamily
    )

    val headerTextRegular = TextStyle(
        fontSize = 30.sp,
        lineHeight = 45.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = poppinsFamily
    )

    // Large Text Styles
    val largeTextBold = TextStyle(
        fontSize = 20.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = poppinsFamily
    )

    val largeTextRegular = TextStyle(
        fontSize = 20.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = poppinsFamily
    )

    // Medium Text Styles
    val mediumTextBold = TextStyle(
        fontSize = 18.sp,
        lineHeight = 27.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = poppinsFamily,
    )

    val mediumTextRegular = TextStyle(
        fontSize = 18.sp,
        lineHeight = 27.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = poppinsFamily
    )

    // Normal Text Styles
    val normalTextBold = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = poppinsFamily
    )

    val normalTextRegular = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = poppinsFamily
    )

    // Small Text Styles
    val smallTextBold = TextStyle(
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = poppinsFamily
    )

    val smallTextRegular = TextStyle(
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = poppinsFamily
    )

    // Smaller Text Styles
    val smallerTextBold = TextStyle(
        fontSize = 11.sp,
        lineHeight = 17.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = poppinsFamily
    )

    val smallerTextSemiBold = TextStyle(
        fontSize = 11.sp,
        lineHeight = 17.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = poppinsFamily
    )

    val smallerTextRegular = TextStyle(
        fontSize = 11.sp,
        lineHeight = 17.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = poppinsFamily
    )

    val smallerTextSmallLabel = TextStyle(
        fontSize = 8.sp,
        lineHeight = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = poppinsFamily
    )
}