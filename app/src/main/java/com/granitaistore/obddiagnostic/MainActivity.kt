package com.granitaistore.obddiagnostic

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val status = findViewById<TextView>(R.id.statusText)
        val connectBtn = findViewById<Button>(R.id.connectBtn)
        val readBtn = findViewById<Button>(R.id.readBtn)
        val result = findViewById<TextView>(R.id.resultText)

        connectBtn.setOnClickListener {
            status.text = "ELM327: not implemented yet"
        }

        readBtn.setOnClickListener {
            result.text = "DTC: no data"
        }
    }
}
