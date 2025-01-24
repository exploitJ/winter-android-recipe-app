package com.surivalcoding.composerecipeapp.data.util

import androidx.annotation.RawRes
import java.io.InputStream

fun interface DemoResponseManager {
    fun open(@RawRes resId: Int): InputStream
}
