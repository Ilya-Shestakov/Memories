package com.example.memories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.memories.StartActivity.Login
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {


    private lateinit var mAuth: FirebaseAuth

    //ID
    lateinit var head_title_memories: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        head_title_memories = findViewById(R.id.head_title_memories)

        mAuth = FirebaseAuth.getInstance()

        head_title_memories.setOnClickListener{
            mAuth.signOut()

            var intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }


    }
}