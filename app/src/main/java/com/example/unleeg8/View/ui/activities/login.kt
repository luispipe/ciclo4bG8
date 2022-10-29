package com.example.unleeg8.View.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.unleeg8.R
import com.example.unleeg8.databinding.ActivityMainBinding

class login: AppCompatActivity() {
    //lateinit var binding: ActivityMainBinding
    lateinit var signupbutton:Button
    lateinit var loginbutton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // binding= ActivityMainBinding.inflate(layoutInflater)
       // setContentView(binding.root)
        setContentView(R.layout.activity_login)
        signupbutton= findViewById(R.id.signup)
        loginbutton= findViewById(R.id.login)

        signupbutton.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
        loginbutton.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }

    }
}