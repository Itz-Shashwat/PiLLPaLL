import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pilpal.R
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class meddusion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    val client = OkHttpClient()
    val url = "https://pilpal.onrender.com/chat"
    val url1 = "https://hacksavvy.onrender.com/chat"

    // First message
    val message = JSONObject().apply {
        put("message", "livocitrizin and crocin")
    }

    val requestBody = message.toString().toRequestBody("application/json".toMediaType())
    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .build()

    val response = client.newCall(request).execute()
    val responseData = JSONObject(response.body?.string())

    println(responseData)

    val responseDataStr = responseData.toString()
    val message2 = JSONObject().apply {
        put("message", "What will be the side effects of $responseDataStr On a person having arrhythmia")
    }

    val requestBody2 = message2.toString().toRequestBody("application/json".toMediaType())
    val request2 = Request.Builder()
        .url(url1)
        .post(requestBody2)
        .build()

    val response2 = client.newCall(request2).execute()
    val responseData2 = JSONObject(response2.body?.string())

    println(responseData2)
}}
