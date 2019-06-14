package com.yifan.rxjavaexample.newsreader

import android.util.Log
import io.reactivex.Observable

class FeedObservable private constructor() {

    companion object {

        fun getFeed(url: String): Observable<List<Entry>> {
            return RawRequestObservable.create(url).map {
                val parser = FeedParser()
                try {
                    val entries: List<Entry> = parser.parse(it.body?.byteStream())
                    Log.i("Yifan", "Number of entries from $url is ${entries.size}")
                    return@map entries
                } catch (e: Exception) {
                    Log.i("Yifan", "Error parsing $e")
                }
                emptyList<Entry>()
            }
        }

    }
}


//{

//}