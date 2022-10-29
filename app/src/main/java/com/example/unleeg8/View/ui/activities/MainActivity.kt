package com.example.unleeg8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.unleeg8.View.ui.activities.login
import com.example.unleeg8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Las siguientes dos lineas son para asociar la activity a un layout
        // es igual que el setContentView(R.layout.activity_main) pero mejor
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Configuración para correr la animación en el layout activity_main
        binding.animationView.setAnimation(R.raw.bookanimation)
        binding.animationView.playAnimation()

        //Configuración para que la transición entre la animación y la
        // siguiente activity se realice tras 4 seg
        handler=Handler(Looper.myLooper()!!)
        handler.postDelayed({
            val intent= Intent(this, login::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }



}