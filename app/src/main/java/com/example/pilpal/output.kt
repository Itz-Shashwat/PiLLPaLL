package com.example.pilpal

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class output: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.output)
        val resultfinal=findViewById<TextView>(R.id.outputTextView)
        val result = intent.getStringExtra("result")
        resultfinal.text = result
    }
}