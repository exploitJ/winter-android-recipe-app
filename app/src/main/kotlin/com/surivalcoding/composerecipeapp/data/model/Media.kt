package com.surivalcoding.composerecipeapp.data.model

import io.ktor.http.*

sealed interface Media {
    data class Image(val url: Url) : Media
    data class Video(val url: Url, val thumbnail: Image) : Media
}
