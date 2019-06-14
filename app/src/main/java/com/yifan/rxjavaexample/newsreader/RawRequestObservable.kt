package com.yifan.rxjavaexample.newsreader

import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class RawRequestObservable private constructor() {

    companion object {
        fun create(url: String): Observable<Response> {

            val client = OkHttpClient()

            return Observable.create<Response> {
                try {
                    val response = client.newCall(Request.Builder().url(url).build()).execute()
                    it.onNext(response)
                    it.onComplete()

                    if (!response.isSuccessful) {
                        it.onError(Exception("Error1"))
                    }
                } catch (e: java.lang.Exception) {
                    Log.i("Yifan", "Error : $e")
                    it.onError(Exception("Error2"))
                }
            }.subscribeOn(Schedulers.io())
        }
    }
}