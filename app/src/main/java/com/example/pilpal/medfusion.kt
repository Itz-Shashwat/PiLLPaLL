package com.example.pilpal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class medfusion : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.medfusion)

        val calculateButton = findViewById<Button>(R.id.submit)
        val tab1 = findViewById<EditText>(R.id.tab1)
        val tab2 = findViewById<EditText>(R.id.tab2)
        val tab3 = findViewById<EditText>(R.id.tab3)
        val sym = findViewById<EditText>(R.id.sym)
        val resultTextView = findViewById<TextView>(R.id.outputTextView)

        calculateButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val client = OkHttpClient()
                val url1 = "https://pilpal.onrender.com/chat"
                val url2 = "https://pill-pal.onrender.com/chat"

                try {
                    // First Message
                    val message1 = JSONObject().apply {
                        put("message", "${tab1.text} and ${tab2.text} and ${tab3.text}")
                    }
                    val requestBody1 = message1.toString().toRequestBody("application/json".toMediaType())
                    val request1 = Request.Builder().url(url1).post(requestBody1).build()

                    val response1 = client.newCall(request1).execute()
                    val responseData1 = JSONObject(response1.body?.string() ?: "")
                    runOnUiThread { resultTextView.text = responseData1.toString() }
                    // Second Message (chained)
                    val message2 = JSONObject().apply {
                        put("message", "How will these $responseData1 medinical compositions all together would react to a person's body who has  $sym ,Note: just give a overview of what to do and what not to do and effect of that on the person nothing else, no extra data.")
                    }
                    val requestBody2 = message2.toString().toRequestBody("application/json".toMediaType())
                    val request2 = Request.Builder().url(url2).post(requestBody2).build()
                    val response2 = client.newCall(request2).execute()
                    val responseData2 = JSONObject(response2.body?.string() ?: "")
                    runOnUiThread { resultTextView.text = responseData2.toString() }
                    val fileName = "file.txt"
                    applicationContext.openFileOutput(fileName, MODE_PRIVATE).use {
                        it.write(responseData2.toString().toByteArray())
                    }

                } catch (e: IOException) {
                    e.printStackTrace()
                    runOnUiThread { resultTextView.text = "Network Error" }  // Handle network errors
                } catch (e: Exception) {
                    e.printStackTrace()
                    runOnUiThread { resultTextView.text = "An Error Occurred" } // Handle other errors
                }
            }
        }
    }

}