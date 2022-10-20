package com.ezamora.test.domain

data class Thumbnail(
    val path: String,
    val extension: String
){
    override fun toString(): String {
        var url = path
        url = url.replace("http:", "https:")
        return "${url}.${extension}"
    }
}