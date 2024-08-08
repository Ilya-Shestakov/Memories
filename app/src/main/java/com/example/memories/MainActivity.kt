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

class MainActivity : AppCompatActivity() {


    //ID
    lateinit var head_title_memories: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TOKEN
        val token = getSharedPreferences("username", Context.MODE_PRIVATE)
        var token_helper = token.getString("loginusername", "").toString()
        Toast.makeText(this, "$token_helper", Toast.LENGTH_SHORT).show()

        head_title_memories = findViewById(R.id.head_title_memories)

        head_title_memories.setOnClickListener{

            var editor = token.edit()
            editor.putString("loginusername", " ")
            editor.commit()

            var intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }


    }
}