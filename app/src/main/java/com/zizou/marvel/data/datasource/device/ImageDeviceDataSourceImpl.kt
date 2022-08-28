package com.zizou.marvel.data.datasource.device

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.zizou.marvel.data.datasource.ImageDeviceDataSource
import com.zizou.marvel.data.device.ImageDownloadWorker
import io.reactivex.rxjava3.core.Single
import java.util.*

class ImageDeviceDataSourceImpl(
    private val context: Context
) : ImageDeviceDataSource {
    override fun downloadImage(url: String): Single<UUID> {
        return Single.just(startDownload(url))
    }

    private fun startDownload(url: String): UUID {
        val data = Data.Builder()
            .putString(ImageDownloadWorker.KEY_URL, url)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<ImageDownloadWorker>()
            .setInputData(data)
            .build()

        WorkManager.getInstance(context)
            .enqueue(workRequest)

        return workRequest.id
    }
}