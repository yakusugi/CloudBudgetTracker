package com.undeniabledreams.cloudbudgettracker.network

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import java.util.*

class NetworkTask(private val context: Context) : AsyncTask<Void?, Void?, String?>() {

    private val gson = GsonBuilder().create()
    private val client = OkHttpClient()
    private lateinit var response: Response

    override fun doInBackground(vararg p0: Void?): String? {
        try {
            // perform network transaction here
            // this code will run on a background thread
            val properties = Properties()

            val inputStream = context.assets.open("server_config.properties")
            properties.load(inputStream)
            val serverUrl = properties.getProperty("server_url")
            val phpLoginFile = properties.getProperty("select_from_currency_file")
            val loginUrl = "$serverUrl$phpLoginFile"
            Log.d("login_url", loginUrl)

            val request = okhttp3.Request.Builder()
                .url(loginUrl)
                .build()

            // Execute request and parse response
            response = client.newCall(request).execute()

            return response.body?.string()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null // Return null if there was an error
    }

}