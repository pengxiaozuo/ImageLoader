package com.peng.glidehanlder

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.peng.imageloader.Request
import com.peng.imageloader.RequestHandler

class GlideRequestHandler : RequestHandler {
    override fun clearMemory(context: Context) {

        Glide.get(context).clearMemory()
    }

    override fun clearDiskCache(context: Context) {
        Glide.get(context).clearDiskCache()
    }


    override fun onLowMemory(context: Context) {
        Glide.get(context).onLowMemory()
    }

    override fun onTrimMemory(context: Context, level: Int) {
        Glide.get(context).onTrimMemory(level)
    }

    override fun <T> load(request: Request<T>) {
        val origin = request.origin
        val requestManager: RequestManager? =
                when (origin) {
                    is Activity -> Glide.with(origin)
                    is Fragment -> Glide.with(origin)
                    is View -> Glide.with(origin)
                    is Context -> Glide.with(origin)
                    else -> null
                }
        var requestBuilder: com.bumptech.glide.RequestBuilder<Drawable>? = null
        when {
            request.url != null -> requestBuilder = requestManager!!.load(request.url)
            request.resId != null -> requestBuilder = requestManager!!.load(request.resId)
            request.file != null -> requestBuilder = requestManager!!.load(request.file)
        }
        applyRequestOption(request, requestBuilder!!)
        requestBuilder.into(request.target!!)
    }

    private fun <T, R> applyRequestOption(request: Request<T>, requestBuilder: com.bumptech.glide.RequestBuilder<R>) {
        val requestOptions = RequestOptions()
        if (request.placeholderResId != null) {
            requestOptions.placeholder(request.placeholderResId!!)
        }
        if (request.placeholderDrawable != null) {
            requestOptions.placeholder(request.placeholderDrawable!!)
        }

        if (request.errorResId != null) {
            requestOptions.error(request.errorResId!!)
        }
        if (request.errorDrawable != null) {
            requestOptions.error(request.errorDrawable!!)
        }
        if (request.targetWidth != null && request.targetHeight != null) {
            requestOptions.override(request.targetWidth!!, request.targetHeight!!)
        }

        if (request.scaleType != null) {
            when (request.scaleType) {
                ImageView.ScaleType.FIT_CENTER,
                ImageView.ScaleType.FIT_START,
                ImageView.ScaleType.FIT_END -> {
                    requestOptions.fitCenter()
                }
                ImageView.ScaleType.FIT_XY -> {
                    requestOptions.optionalCenterInside()
                }
                ImageView.ScaleType.CENTER_INSIDE -> {
                    requestOptions.centerInside()
                }
                ImageView.ScaleType.CENTER -> {
                }
                ImageView.ScaleType.MATRIX -> {
                }
                ImageView.ScaleType.CENTER_CROP -> {
                    requestOptions.centerCrop()
                }
            }
        }
        if (request.circleCrop) {
            requestOptions.circleCrop()
        }
        if (request.roundedCorners != null) {
            val rc = RoundedCorners(request.roundedCorners!!)
            requestOptions.transform(rc)
        }

        requestOptions.onlyRetrieveFromCache(request.onlyReadDiskCache)

        requestOptions.skipMemoryCache(request.skipMemoryCache)

        requestBuilder.apply(requestOptions)
    }

    override fun <T> cancel(request: Request<T>) {
        val origin = request.origin
        val requestManager: RequestManager? =
                when (origin) {
                    is Activity -> Glide.with(origin)
                    is Fragment -> Glide.with(origin)
                    is View -> Glide.with(origin)
                    is Context -> Glide.with(origin)
                    else -> null
                }
        requestManager?.clear(request.target!!)
    }

    override fun <T> get(request: Request<T>): Bitmap? {
        val origin = request.origin
        val requestManager: RequestManager? =
                when (origin) {
                    is Activity -> Glide.with(origin)
                    is Fragment -> Glide.with(origin)
                    is View -> Glide.with(origin)
                    is Context -> Glide.with(origin)
                    else -> null
                }
        var requestBuilder: com.bumptech.glide.RequestBuilder<Drawable>? = null
        when {
            request.url != null -> requestBuilder = requestManager!!.load(request.url)
            request.resId != null -> requestBuilder = requestManager!!.load(request.resId)
            request.file != null -> requestBuilder = requestManager!!.load(request.file)
        }
        applyRequestOption(request, requestBuilder!!)
        return requestManager!!.asBitmap().submit().get()
    }


}