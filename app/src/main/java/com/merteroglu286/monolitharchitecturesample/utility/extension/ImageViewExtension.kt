package com.merteroglu286.monolitharchitecturesample.utility.extension

import android.app.Activity
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.io.File

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String) {
    if (url.isEmpty()) return
    Glide
        .with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .fitCenter()
        .into(this)
}


fun uriToFile(activity: Activity, uri: Uri?): File? {
    if (uri == null)
        return null

    var filePath: String
    val cursor = activity.contentResolver.query(uri, null, null, null, null)
    if (cursor == null) {
        filePath = uri.path.orEmpty()
    } else {
        try {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            filePath = cursor.getString(idx)
            cursor.close()
        } catch (e: RuntimeException) {
            filePath =
                PathUtils.getFilePathForN(activity, uri)
        }
    }
    return File(filePath)
}
