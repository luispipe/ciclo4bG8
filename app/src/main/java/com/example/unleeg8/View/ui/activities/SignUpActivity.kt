package com.example.unleeg8.View.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.unleeg8.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpActivity:AppCompatActivity() {
    lateinit var btnregister:Button
    lateinit var firebaseAuth: FirebaseAuth

    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        firebaseAuth= Firebase.auth
        database= Firebase.database.reference.child("User")
        val name= findViewById<EditText>(R.id.signupName)
        val email= findViewById<EditText>(R.id.signupEmail)
        val birthdate= findViewById<EditText>(R.id.signupBirthdate)
        val phone= findViewById<EditText>(R.id.signupPhone)
        val password= findViewById<EditText>(R.id.signupPassword)

        btnregister= findViewById(R.id.register)
        btnregister.setOnClickListener {
            createUser(email.text.toString(),password.text.toString(),
                name.text.toString(),birthdate.text.toString(),phone.text.toString()
                )
        }
    }

    fun createUser(email:String,password:String,name:String,birthdate:String,phone:String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                Task-> if(Task.isSuccessful){
                    val user= firebaseAuth.currentUser
                val db= database.child(user?.uid.toString())
                db.child("name").setValue(name)
                db.child("birthdate").setValue(birthdate)
                db.child("phone").setValue(phone)

                startActivity(Intent(this,login::class.java))
                }else{
                    Toast.makeText(baseContext,"try again",Toast.LENGTH_LONG).show()
                }
            }
    }
}