package com.example.pilpal

import DatabaseHelper
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)

        val buttonZero = findViewById<Button>(R.id.submit)
        buttonZero.setOnClickListener {
            val name = findViewById<EditText>(R.id.nameEditText).text.toString()
            val ageText = findViewById<EditText>(R.id.ageEditText).text.toString()
            val gender = findViewById<EditText>(R.id.genderEditText).text.toString()

            if (name.isNotEmpty() && ageText.isNotEmpty() && gender.isNotEmpty()) {
                val age = ageText.toIntOrNull()
                if (age != null) {
                    insertData(name, age, gender)
                    val intent = Intent(this, home::class.java)
                    startActivity(intent)
                } else {
                    showAlert("Invalid Age", "Please enter a valid age")
                }
            } else {
                showAlert("Excuse ME", "Please Fill The Details")
            }
        }
    }

    private fun insertData(name: String, age: Int, gender: String) {
        try {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(DatabaseContract.UserInfo.COLUMN_NAME_NAME, name)
                put(DatabaseContract.UserInfo.COLUMN_NAME_AGE, age)
                put(DatabaseContract.UserInfo.COLUMN_NAME_GENDER, gender)
            }
            val newRowId = db.insert(DatabaseContract.UserInfo.TABLE_NAME, null, values)
            db.close()
        } catch (e: Exception) {
            showAlert("Error", "Failed to insert data: ${e.message}")
        }
    }

    private fun showAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}