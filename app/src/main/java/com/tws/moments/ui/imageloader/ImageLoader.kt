package com.tws.moments.ui.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun displayImage(url: String?, imageView: ImageView)
}