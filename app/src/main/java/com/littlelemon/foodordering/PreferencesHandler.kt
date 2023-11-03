package com.littlelemon.foodordering

import android.content.Context
import android.content.SharedPreferences

class PreferencesHandler(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

    fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun clearAll(){
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

}
