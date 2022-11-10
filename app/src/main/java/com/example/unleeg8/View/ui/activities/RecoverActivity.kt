package com.example.unleeg8.View.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.unleeg8.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecoverActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)

        firebaseAuth= Firebase.auth
        val email= findViewById<EditText>(R.id.restoreEmail)
        val btnChange= findViewById<Button>(R.id.restore)
        btnChange.setOnClickListener {
            restorePassword(email.text.toString())
            // email --> EditText
            // email.text --> Object
            // email.text.toString() --> String
        }

    }
    fun restorePassword(email:String){
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this){
                Task-> if (Task.isSuccessful){
                startActivity(Intent(this, login::class.java))
            }else{
                Toast.makeText(this, "Restore Fail", Toast.LENGTH_LONG).show()
                }
            }
    }

}