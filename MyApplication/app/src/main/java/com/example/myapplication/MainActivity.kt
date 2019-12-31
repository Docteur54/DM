package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newButton: Button = findViewById(R.id.createButton)
        newButton.setOnClickListener { openForm() }
    }

    private fun openForm() {
        val intent = Intent( this  ,TaskFormActivity::class.java)
        startActivity(intent)
    }
}
