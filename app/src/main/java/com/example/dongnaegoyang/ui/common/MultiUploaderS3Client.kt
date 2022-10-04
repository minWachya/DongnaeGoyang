package com.example.dongnaegoyang.ui.common

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.example.dongnaegoyang.ui.cat_add.CatAddViewModel
import io.reactivex.Observable
import io.reactivex.Single
import java.io.File
import java.net.URL

class MultiUploaderS3Client(private val bucketName: String, context: Context, val vm: CatAddViewModel) {
    private var maxSize = 0
    lateinit var listImageUrl: Array<String>
    private var count = 0

    private val ai: ApplicationInfo = context.packageManager
        .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
    private val ak: String = ai.metaData["accessKey"].toString()
    private val sak: String = ai.metaData["secretAccessKey"].toString()
    private val wsCredentials: BasicAWSCredentials = BasicAWSCredentials(ak, sak)
    private val s3Client: AmazonS3Client =
        AmazonS3Client(wsCredentials, Region.getRegion(Regions.AP_NORTHEAST_2))
    private val transferUtility: TransferUtility = TransferUtility.builder()
        .s3Client(s3Client)
        .context(context)
        .build()

    init {
        TransferNetworkLossHandler.getInstance(context)
    }

    fun uploadMultiple(fileToKeyUploads: LinkedHashMap<String, File>): Single<MutableList<Unit>>? {
        maxSize = fileToKeyUploads.size
        listImageUrl = Array(maxSize) { "" }
        return Observable.fromIterable(fileToKeyUploads.entries)
            .concatMapEager {
                uploadSingle(
                    transferUtility,
                    it.value,
                    it.key,
                    count++
                ).toObservable()
            }.toList()
    }

    private fun uploadSingle(
        transferUtility: TransferUtility?,
        aLocalFile: File?,
        toRemoteKey: String?,
        index: Int
    ): Single<Unit> {
        return Single.create {
            transferUtility?.upload(bucketName, toRemoteKey, aLocalFile)
                ?.setTransferListener(object : TransferListener {
                    override fun onStateChanged(id: Int, state: TransferState?) {
                        if (state == TransferState.COMPLETED) {
                            val s3Url: URL = s3Client.getUrl(bucketName, toRemoteKey)
                            listImageUrl[index] = s3Url.toString()
                            if(listImageUrl.size == maxSize) {
                                vm.setArrS3Url(listImageUrl)
                            }
                        }
                    }

                    override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {
                        val done = bytesCurrent / bytesTotal * 100.0
                        Log.d("mmm", "UPLOAD - - ID: $id, percent done = $done")
                    }

                    override fun onError(id: Int, ex: Exception?) {
                        Log.d("mmm", "UPLOAD ERROR - - ID: $id - - EX:" + ex.toString())
                    }
                })
        }


    }
}
