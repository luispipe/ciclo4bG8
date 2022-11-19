package com.example.unleeg8.View.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.unleeg8.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    lateinit var firebaseAuth: FirebaseAuth
    var database:DatabaseReference= FirebaseDatabase.getInstance().getReference("User")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth= Firebase.auth
        val user= firebaseAuth.currentUser
        val name= view.findViewById<EditText>(R.id.nameProfile)
        val phone= view.findViewById<EditText>(R.id.phoneProfile)
        val birthDate= view.findViewById<EditText>(R.id.birthProfile)
        val email= view.findViewById<EditText>(R.id.emailProfile)
        val editName= view.findViewById<ImageButton>(R.id.editName)
        val editEmail= view.findViewById<ImageButton>(R.id.editEmail)
        val editBirth= view.findViewById<ImageButton>(R.id.editBirth)
        val editPhone= view.findViewById<ImageButton>(R.id.editPhone)

        editName.setOnClickListener{
            database.child(user?.uid.toString()).child("name").setValue(name.text.toString()).addOnSuccessListener {
                Toast.makeText(this.context,"Se cambio el nombre",Toast.LENGTH_LONG).show()
            }
        }

        editPhone.setOnClickListener{
            database.child(user?.uid.toString()).child("phone").setValue(phone.text.toString()).addOnSuccessListener {
                Toast.makeText(this.context,"Se cambio el telefono",Toast.LENGTH_LONG).show()
            }
        }

        editBirth.setOnClickListener{
            database.child(user?.uid.toString()).child("birthdate").setValue(birthDate.text.toString()).addOnSuccessListener {
                Toast.makeText(this.context,"Se cambio la fecha",Toast.LENGTH_LONG).show()
            }
        }


        editEmail.setOnClickListener{
            user?.updateEmail(email.text.toString())
        }




        email.setText(user?.email.toString())

        database.child(user?.uid.toString()).addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                name.setText(snapshot.child("name").value.toString())
                birthDate.setText(snapshot.child("birthdate").value.toString())
                phone.setText(snapshot.child("phone").value.toString())
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
     /*
        database.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children){
                    name.setText(ds.child("name").value.toString())
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }

        })*/


    }

}