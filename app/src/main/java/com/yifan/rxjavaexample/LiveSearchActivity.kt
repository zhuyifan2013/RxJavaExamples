package com.yifan.rxjavaexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.live_search.*
import java.util.concurrent.TimeUnit


class LiveSearchActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.live_search)

        input.textChanges().filter { text -> text.length >= 3 }
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateList)

    }

    private fun updateList(s:CharSequence) {
        Log.i("Yifan", "New chars11 : $s")
    }

}