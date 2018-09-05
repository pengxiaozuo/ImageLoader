package com.peng.imageloader

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import java.io.File

class RequestBuilder<T>(internal var origin: T, private val handler: RequestHandler) {

    private val request = Request(this)

    fun load(url: String): RequestBuilder<T> {
        if (request.resId != null) {
            throw IllegalArgumentException("You cannot call load multiple times")
        }
        if (request.file != null) {
            throw IllegalArgumentException("You cannot call load multiple times")
        }
        request.url = url
        return this
    }

    fun load(resId: Int): RequestBuilder<T> {
        if (request.url != null) {
            throw IllegalArgumentException("You cannot call load multiple times")
        }
        if (request.file != null) {
            throw IllegalArgumentException("You cannot call load multiple times")
        }
        request.resId = resId
        return this
    }

    fun load(file: File): RequestBuilder<T> {
        if (request.url != null) {
            throw IllegalArgumentException("You cannot call load multiple times")
        }
        if (request.resId != null) {
            throw IllegalArgumentException("You cannot call load multiple times")
        }
        request.file = file
        return this
    }

    fun placeholder(resId: Int): RequestBuilder<T> {
        if (request.placeholderDrawable != null) {
            throw IllegalArgumentException("You cannot call placeholder multiple times")
        }
        request.placeholderResId = resId
        return this
    }

    fun placeholder(drawable: Drawable): RequestBuilder<T> {
        if (request.placeholderResId != null) {
            throw IllegalArgumentException("You cannot call placeholder multiple times")
        }
        request.placeholderDrawable = drawable
        return this
    }

    fun error(resId: Int): RequestBuilder<T> {
        if (request.errorDrawable != null) {
            throw IllegalArgumentException("You cannot call error multiple times")
        }
        request.errorResId = resId
        return this
    }

    fun error(drawable: Drawable): RequestBuilder<T> {
        if (request.errorResId != null) {
            throw IllegalArgumentException("You cannot call error multiple times")
        }
        request.errorDrawable = drawable
        return this
    }

    fun resize(targetWidth: Int, targetHeight: Int): RequestBuilder<T> {
        request.targetWidth = targetWidth
        request.targetHeight = targetHeight
        return this
    }

    fun centerCrop(): RequestBuilder<T> {
        request.scaleType = ImageView.ScaleType.CENTER_CROP
        return this
    }

    fun centerInside(): RequestBuilder<T> {
        request.scaleType = ImageView.ScaleType.CENTER_INSIDE
        return this
    }

    fun fitCenter(): RequestBuilder<T> {
        request.scaleType = ImageView.ScaleType.FIT_CENTER
        return this
    }

    fun scaleType(scaleType: ImageView.ScaleType): RequestBuilder<T> {
        request.scaleType = scaleType
        return this
    }

    fun roundedCorners(radius: Int): RequestBuilder<T> {
        request.roundedCorners = radius
        return this
    }

    fun circleCrop(): RequestBuilder<T> {
        request.circleCrop = true
        return this
    }

    fun onlyReadDiskCache(): RequestBuilder<T> {
        request.onlyReadDiskCache = true
        return this
    }
    fun skipMemoryCache(): RequestBuilder<T> {
        request.skipMemoryCache = true
        return this
    }


    fun into(imageView: ImageView) {
        if (request.resId == null && request.file == null && request.url == null) {
            throw IllegalArgumentException("You must call load")
        }
        request.target = imageView
        handler.load(request)
    }

    fun cancel(imageView: ImageView) {
        request.cancel = imageView
        handler.cancel(request)
    }

    fun get(): Bitmap? {
        if (request.resId == null && request.file == null && request.url == null) {
            throw IllegalArgumentException("You must call load")
        }
        return handler.get(request)
    }

}