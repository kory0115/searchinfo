package com.example.searchinfo.preference

import android.content.Context
import android.content.SharedPreferences


class SharedPreferences(context: Context) {
    private val pref : SharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    fun saveData(data : String) {
        pref.edit().putString("save", data).apply()
    }

    fun loadData(): String? {
        return pref.getString("save", "")
    }

    private val islike : SharedPreferences = context.getSharedPreferences("islike", Context.MODE_PRIVATE)

    fun saveLike(url: String, like : Boolean) {
        islike.edit().putBoolean(url, like).apply()
    }

    fun loadLike(url: String): Boolean {
        return islike.getBoolean(url, false)
    }

    fun removeLike(url: String) {
        pref.edit().remove(url).apply()
    }


}