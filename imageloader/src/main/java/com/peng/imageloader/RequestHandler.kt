package com.peng.imageloader

import android.content.Context
import android.graphics.Bitmap

interface RequestHandler {
    fun <T> load(request: Request<T>)
    fun <T> cancel(request: Request<T>)
    fun <T> get(request: Request<T>): Bitmap?
    fun clearMemory(context: Context)
    fun clearDiskCache(context: Context)
    fun onLowMemory(context: Context)
    fun onTrimMemory(context: Context, level: Int)
}