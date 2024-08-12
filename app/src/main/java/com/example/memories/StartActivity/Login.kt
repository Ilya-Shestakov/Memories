package com.example.memories.StartActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import com.example.memories.MainActivity
import com.example.memories.R
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class Login : AppCompatActivity() {


    //ID
    lateinit var btn_registration_in_login: ConstraintLayout
    lateinit var btn_next_login: ConstraintLayout
    lateinit var checkbox_remember_me: CheckBox


    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_registration_in_login = findViewById(R.id.btn_registration_in_login)
        btn_next_login = findViewById(R.id.btn_next_login)

        //TOKEN
        var token = getSharedPreferences("username", Context.MODE_PRIVATE)


        btn_registration_in_login.setOnClickListener{
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
            finish()
        }



        if (token.getString("loginusername", " ")!= " "){
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        mAuth = FirebaseAuth.getInstance()


        btn_next_login.setOnClickListener {

            loginUser()

        }

    }

    private fun loginUser() {

        val edit_email_login: String = findViewById<EditText>(R.id.edit_email_login).text.toString()
        val edit_password_login: String= findViewById<EditText>(R.id.edit_password_login).text.toString()


        if (edit_email_login == "") {
            Toast.makeText(this, "Enter the email", Toast.LENGTH_SHORT).show()
        } else if (edit_password_login == "") {
            Toast.makeText(this, "Enter the password", Toast.LENGTH_SHORT).show()
        } else
        {
            mAuth.signInWithEmailAndPassword(edit_email_login, edit_password_login)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this, task.exception!!.toString(), Toast.LENGTH_LONG).show()
                    }
                }
        }


    }

    private fun letsMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}