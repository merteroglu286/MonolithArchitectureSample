package com.merteroglu286.monolitharchitecturesample.utility.extension

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object PathUtils {
    fun getFilePathForN(context: Context, uri: Uri): String {
        val returnUri: Uri = uri
        val returnCursor: Cursor? =
            context.contentResolver.query(returnUri, null, null, null, null)
        /*
         * Get the column indexes of the data in the Cursor,
         * move to the first row in the Cursor, get the data,
         * and display it.
         */
        val nameIndex: Int = returnCursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME) ?: -1
        val sizeIndex: Int = returnCursor?.getColumnIndex(OpenableColumns.SIZE) ?: -1
        returnCursor?.moveToFirst()
        val name: String? = returnCursor?.getString(nameIndex)
        val size: String? = returnCursor?.getLong(sizeIndex)?.toString()
        val file = File(context.filesDir, name)
        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)
            var read: Int
            val maxBufferSize = 1 * 1024 * 1024
            val bytesAvailable: Int = inputStream?.available() ?: 0
            val bufferSize = bytesAvailable.coerceAtMost(maxBufferSize)
            val buffers = ByteArray(bufferSize)
            while (inputStream?.read(buffers).also { read = it ?: -1 } != -1) {
                outputStream.write(buffers, 0, read)
            }
            Log.e("File Size", "Size " + file.length())
            inputStream?.close()
            outputStream.close()
            Log.e("File Path", "Path " + file.path)
            Log.e("File Size", "Size " + file.length())
        } catch (e: Exception) {
            Log.e("Exception", e.message.orEmpty())
        }
        return file.path
    }
}