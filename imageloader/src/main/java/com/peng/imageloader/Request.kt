package com.peng.imageloader

import android.graphics.drawable.Drawable
import android.widget.ImageView
import java.io.File

class Request<T>(builder: RequestBuilder<T>) {
    var origin: T = builder.origin
    var url: String? = null
    var resId: Int? = null
    var file: File? = null
    var placeholderResId: Int? = null
    var placeholderDrawable: Drawable? = null
    var errorResId: Int? = null
    var errorDrawable: Drawable? = null
    var targetWidth: Int? = null
    var targetHeight: Int? = null
    var target: ImageView? = null
    var cancel: ImageView? = null
    var scaleType: ImageView.ScaleType? = null
    var roundedCorners: Int? = null
    var circleCrop = false
    var onlyReadDiskCache = false
    var skipMemoryCache = false

}