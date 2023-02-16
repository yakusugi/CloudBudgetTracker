package com.undeniabledreams.cloudbudgettracker.dao

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.ServerError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.undeniabledreams.cloudbudgettracker.core.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap

class BudgetTrackerExpenseInsertDao(context: Context) {
    private val context: Context = context.applicationContext

    fun insertIntoProductType(productTypeDto: ProductTypeDto, storeDto: StoreDto, budgetTrackerMasterDto: BudgetTrackerMasterDto): Int {

        try {
            val properties = Properties()
            val inputStream = context.assets.open("server_config.properties")
            properties.load(inputStream)
            val serverUrl = properties.getProperty("server_url")
            val phpInsertFile = properties.getProperty("insert_product_type_file")
            val insertUrl = "$serverUrl$phpInsertFile"

            val stringRequest = object : StringRequest(
                Method.POST, insertUrl,
                Response.Listener { response ->
                    try {
                        val jsonObject = JSONObject(response)
                        val success = jsonObject.getString("success")

                        if (success > 0.toString()) {
                            Log.d(TAG, "insertIntoProductType: Product type data data has been inserted")
                        }

                    } catch (e: JSONException) {
                        Log.e("JSONException", e.toString())
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    if (error is ServerError) {
                        val networkResponse = error.networkResponse
                        if (networkResponse != null) {
                            Log.e(
                                "Volley",
                                "Error. HTTP Status Code: ${networkResponse.statusCode}"
                            )
                        }
                    }
                    Toast.makeText(context, "Unable to insert Product type $error", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("VolleyError", error.toString())
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["date"] = budgetTrackerMasterDto.getDate().toString()
                    params["price"] = budgetTrackerMasterDto.getPrice().toString()
                    params["vat"] = budgetTrackerMasterDto.getVat().toString()
                    params["product_type_name"] = productTypeDto.getProductTypeName()
                    params["product_name"] = budgetTrackerMasterDto.getProductName()!!
                    params["store_name"] = storeDto.getStoreName()

                    return params
                }
            }

            val requestQueue = Volley.newRequestQueue(context)
            requestQueue.add(stringRequest)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return 0
    }

}