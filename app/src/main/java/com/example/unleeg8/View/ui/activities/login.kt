package com.example.unleeg8.View.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.unleeg8.R
import com.example.unleeg8.databinding.ActivityMainBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class login: AppCompatActivity() {
    //lateinit var binding: ActivityMainBinding
    lateinit var signupbutton:Button
    lateinit var loginbutton: Button
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // binding= ActivityMainBinding.inflate(layoutInflater)
       // setContentView(binding.root)
        setContentView(R.layout.activity_login)

        firebaseAuth=Firebase.auth
        val email= findViewById<EditText>(R.id.loginEmail)
        val password= findViewById<EditText>(R.id.loginPassword)

        signupbutton= findViewById(R.id.signup)
        loginbutton= findViewById(R.id.login)

        signupbutton.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
        loginbutton.setOnClickListener {
            user_login(email.text.toString(),password.text.toString())
        }

        val txtLossPass= findViewById<TextView>(R.id.lossPass)
        txtLossPass.setOnClickListener {
            startActivity(Intent(this,RecoverActivity::class.java))
        }
    }

    fun user_login(email:String,password:String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                Task-> if(Task.isSuccessful){
                startActivity(Intent(this,HomeActivity::class.java))
                }else{
                    Toast.makeText(baseContext,"ERROR",Toast.LENGTH_LONG).show()
                }
            }
    }

}