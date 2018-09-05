package com.peng.imageloader

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View

/**
 * 图片加载框架封装
 */
object ImageLoader {

    var handler: RequestHandler? = null


    fun with(activity: Activity): RequestBuilder<Activity> {
        checkConfig()
        return RequestBuilder(activity, handler!!)
    }

    fun with(fragment: Fragment): RequestBuilder<Fragment> {
        checkConfig()
        return RequestBuilder(fragment, handler!!)
    }

    fun with(view: View): RequestBuilder<View> {
        checkConfig()
        return RequestBuilder(view, handler!!)
    }

    fun with(context: Context): RequestBuilder<Context> {
        checkConfig()
        return RequestBuilder(context, handler!!)
    }

    fun clearMemory(context: Context) {
        handler?.clearMemory(context)
    }

    fun clearDiskCache(context: Context) {
        handler?.clearDiskCache(context)
    }

    fun onLowMemory(context: Context) {
        handler?.onLowMemory(context)
    }

    fun onTrimMemory(context: Context, level: Int) {
        handler?.onTrimMemory(context, level)
    }


    private fun checkConfig() {
        if (handler == null) {
            throw IllegalArgumentException("must init")
        }
    }


}