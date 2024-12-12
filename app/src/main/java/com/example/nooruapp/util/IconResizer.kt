package com.example.nooruapp.util

object IconResizer {
    //The default response we get from weatherapi provides us with a 64x64px
    //icon, which when stretched out looks not great, so grab the 128x128px they also provide
    fun resizeIcon(url: String): String{
        val r = Regex("64x64")
        return url.replace(r,"128x128")
    }
}