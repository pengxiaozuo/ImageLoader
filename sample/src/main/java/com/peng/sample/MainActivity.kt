package com.peng.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.peng.glidehanlder.GlideRequestHandler
import com.peng.imageloader.ImageLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ImageLoader.handler = GlideRequestHandler()

        ImageLoader.with(this)
                .load("https://pic2.zhimg.com/80/f457f33fd488eab4746e4b0acf643de5_hd.jpg")
                .circleCrop()
                .into(iv)

    }
}
