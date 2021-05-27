package com.jamshidbek.casharraylist.cash

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jamshidbek.casharraylist.model.User

object CashSharedPreference {
    private const val NAME = "KeshXotiraFayli"
    private const val MODE = Context.MODE_APPEND
    private lateinit var preferences: SharedPreferences

    @SuppressLint("WrongConstant")
    fun init(context: Context){
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation:(SharedPreferences.Editor)-> Unit){
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var objectString : ArrayList<User>?
        get() = gsonStringToArray(preferences.getString("object", "[]")!!)
        set(value) = preferences.edit{
            if(value != null){
                it.putString("object", arrayToGsonString(value))
            }
        }
}

fun arrayToGsonString(list:ArrayList<User>):String{
    return Gson().toJson(list)
}

fun gsonStringToArray(gsonString: String):ArrayList<User>{
    val type = object : TypeToken<ArrayList<User>>(){}.type
    return Gson().fromJson(gsonString, type)
}