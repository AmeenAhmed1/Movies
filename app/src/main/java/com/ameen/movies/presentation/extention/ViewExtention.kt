package com.ameen.movies.presentation.extention

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.ameen.movies.R
import com.ameen.movies.core.util.API_USER_AGENT
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(this.context).load(
        GlideUrl(
            url,
            LazyHeaders.Builder().addHeader("User-Agent", API_USER_AGENT.toString()).build()
        )
    ).placeholder(R.drawable.ic_launcher_foreground).into(this)
}