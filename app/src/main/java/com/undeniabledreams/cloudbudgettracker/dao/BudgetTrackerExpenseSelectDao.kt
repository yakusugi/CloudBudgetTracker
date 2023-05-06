package com.undeniabledreams.cloudbudgettracker.dao

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.undeniabledreams.cloudbudgettracker.core.BudgetTrackerUserDto
import com.undeniabledreams.cloudbudgettracker.core.CurrencyDto
import com.undeniabledreams.cloudbudgettracker.network.NetworkTask
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap
import okhttp3.OkHttpClient

class BudgetTrackerExpenseSelectDao(private val context: Context) {

    private val gson = GsonBuilder().create()

    fun selectFromCurrency(currencyDto: CurrencyDto): List<CurrencyDto> {
        val networkTask = NetworkTask(context)
        val responseString = networkTask.execute().get() ?: return emptyList()

        try {
            val jsonObject = JSONObject(responseString)
            if (jsonObject.has("success") && jsonObject.getInt("success") == 1 && jsonObject.has("details")) {
                val responseData = gson.fromJson(jsonObject.getJSONArray("details").toString(), Array<CurrencyDto>::class.java)

                // Create a list to hold the data retrieved from the API
                val currencyInfoList = mutableListOf<Pair<String, String>>()

                for (i in 0 until jsonObject.length()) {
                    val currencyJsonObject = jsonObject.getJSONObject(i.toString())
                    val currencyName = currencyJsonObject.getString("currency_name")
                    val currencyAddr = currencyJsonObject.getString("currency_addr")
                    val currencyInfo = Pair(currencyName, currencyAddr)
                    currencyInfoList.add(currencyInfo)
                }


                return responseData.toList()
            }
        } catch (e: JSONException) {
            Log.e("BudgetTrackerExpenseSelectDao", "JSON parsing error: ${e.message}")
        } catch (e: Exception) {
            Log.e("BudgetTrackerExpenseSelectDao", "Error: ${e.message}")
        }

        return emptyList()
    }


}
