package com.zizou.marvel.data.device

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.net.URL

class ImageDownloadWorker(
    private val context: Context,
    param: WorkerParameters
) : Worker(context, param) {
    override fun doWork(): Result {
        val url = inputData.getString(KEY_URL) ?: return Result.failure()

        try {
            download(url)
        } catch (e: Exception) {
            return Result.failure()
        }

        return Result.success()
    }

    private fun download(downloadUrl: String) {
        val fileName = URL(downloadUrl).file
        val inputStream = URL(downloadUrl).openStream()
        val bitmap = BitmapFactory.decodeStream(inputStream)

        saveImageToGallery(bitmap, fileName)
    }

    private fun saveImageToGallery(bitmap: Bitmap, filename: String) {
        val imageOutStream: OutputStream

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, filename)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }

            context.contentResolver.run {
                val uri = insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values) ?: return
                imageOutStream = openOutputStream(uri) ?: return
            }
        } else {
            val imagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath
            val image = File(imagePath, filename)
            imageOutStream = FileOutputStream(image)
        }

        imageOutStream.use { bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it) }
    }

    companion object {
        const val KEY_URL = "key_url"
    }
}