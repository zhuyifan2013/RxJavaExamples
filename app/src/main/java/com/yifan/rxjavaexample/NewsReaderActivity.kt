package com.yifan.rxjavaexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.yifan.rxjavaexample.newsreader.Entry
import com.yifan.rxjavaexample.newsreader.FeedObservable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

// 1. get the urls
// 2.
class NewsReaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.live_search)
        val urls = listOf("https://news.google.com/?output=atom",
                "http://www.theregister.co.uk/software/headlines.atom",
                "http://www.linux.com/news/soware?format=feed&type=atom")

        val observableList: MutableList<Observable<List<Entry>>> = emptyList<Observable<List<Entry>>>().toMutableList()

        for (url: String in urls) {
            observableList.add(FeedObservable.getFeed(url).onErrorReturn {
                Log.i("Yifan", "Wrong : $it")
                emptyList()
            })
        }

        Observable.combineLatest(observableList) { it ->
            val combindedList = emptyList<Entry>().toMutableList()

            for (list in it) {
                combindedList.addAll(list as List<Entry>)
            }

            return@combineLatest combindedList
        }.map {
            val list = mutableListOf<Entry>()
            list.addAll(it)
            list.sort()
            return@map list
        }.flatMap { Observable.fromIterable(it) }
                .take(6)
                .map { it.toString() }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateList)


    }

    fun updateList(listItems: List<String>) {
        for (item: String in listItems) {
            Log.i("Yifan", "Item : $item")
        }
    }
}