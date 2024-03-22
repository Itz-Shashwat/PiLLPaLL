package com.example.pilpal

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class home : AppCompatActivity() {
    @SuppressLint("RestrictedApi", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        val spinner: Spinner = findViewById(R.id.spin)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                when (selectedItem) {
                    "MedFusion" -> {
                        val intent = Intent(this@home, medfusion::class.java)
                        startActivity(intent)
                    }
                    "The Hub" -> {
                        val url = "https://sriyachikki.wixsite.com/the-pill-pal-hub"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    }
                    "Mr.Pal" -> {
                        val url = "https://2c847136fc4ecb27ce.gradio.live"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
    }
}
