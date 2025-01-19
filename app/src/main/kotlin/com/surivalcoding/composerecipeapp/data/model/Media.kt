package com.surivalcoding.composerecipeapp.data.model

import coil3.Uri

sealed interface Media {
    data class Image(val url: Uri) : Media
    data class Video(val url: Uri, val thumbnail: Image) : Media
}
