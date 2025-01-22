package com.surivalcoding.composerecipeapp.presentation.shared

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

class CutoutShape(
    private val cutoutWidth: Dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val pxWidth = with(density) { cutoutWidth.toPx() }
        val pxHeight = pxWidth / 3f

        // Calculate control points for smooth transition
        val centerX = size.width / 2

        val x1Modifier = 0.28f
        val x2Modifier = 0.23f

        val y1Modifier = 0f
        val y2Modifier = 1f
        val y3Modifier = 1f

        val topLeft = Offset(centerX - pxWidth / 2, 0f)
        val bottomRight = Offset(centerX + pxWidth / 2, pxHeight)
        val delta =  bottomRight - topLeft

        return Outline.Generic(
            Path().apply {
                moveTo(0f, 0f)
                lineTo(0f, size.height)
                lineTo(size.width, size.height)
                lineTo(size.width, 0f)
                close()
            } - Path().apply {
                moveTo(topLeft.x, topLeft.y)
                relativeCubicTo(
                    pxWidth * x1Modifier, delta.y * y1Modifier,
                    pxWidth * x2Modifier, delta.y * y2Modifier,
                    pxWidth / 2, delta.y * y3Modifier
                )
                cubicTo(
                    bottomRight.x - delta.x* x2Modifier, bottomRight.y * y2Modifier,
                    bottomRight.x - delta.x* x1Modifier, bottomRight.y * y1Modifier,
                    bottomRight.x, 0f
                )
            })
    }
}