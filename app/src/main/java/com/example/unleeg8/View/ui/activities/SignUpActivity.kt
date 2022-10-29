package com.example.unleeg8.View.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.unleeg8.R

class SignUpActivity:AppCompatActivity() {
    lateinit var btnregister:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btnregister= findViewById(R.id.register)
        btnregister.setOnClickListener {
            startActivity(Intent(this,login::class.java))
        }
    }
}