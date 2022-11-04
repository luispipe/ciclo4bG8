package com.example.unleeg8.View.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.unleeg8.R

class RecoverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)

        val btnChange= findViewById<Button>(R.id.buttonChange)
        btnChange.setOnClickListener {
            startActivity(Intent(this, login::class.java))
        }

    }
}