package com.yifan.rxjavaexample.newsreader

data class Entry(
        val id: String,
        val title: String,
        val link: String,
        val updated: Long
) : Comparable<Entry> {

    override fun compareTo(other: Entry): Int {
        return 0
    }
}