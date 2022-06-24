package com.ameen.movies.presentation.extention

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.ameen.movies.R
import com.ameen.movies.core.util.API_USER_AGENT
import com.ameen.movies.core.util.IMAGE_BASE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImageFromUrl(url: String, loadingProgressBar: ProgressBar) {
    Glide.with(this.context).load(
        GlideUrl(
            IMAGE_BASE_URL + url,
            LazyHeaders.Builder().addHeader("User-Agent", API_USER_AGENT.toString()).build()
        )
    )
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                loadingProgressBar.hide()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                loadingProgressBar.hide()
                return false
            }

        })
        .centerCrop()
        .error(R.drawable.ic_error)
        .into(this)
}