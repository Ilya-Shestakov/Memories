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
import org.w3c.dom.Text

class Login : AppCompatActivity() {


    //ID
    lateinit var btn_registration_in_login: ConstraintLayout
    lateinit var btn_next_login: ConstraintLayout
    lateinit var checkbox_remember_me: CheckBox
    lateinit var edit_username_login: EditText
    lateinit var edit_password_login: EditText


    //HELPER
    var Username: String = "root"
    var Password: String = "123"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_registration_in_login = findViewById(R.id.btn_registration_in_login)
        btn_next_login = findViewById(R.id.btn_next_login)
        checkbox_remember_me = findViewById(R.id.checkbox_remember_me)

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



        btn_next_login.setOnClickListener {


            edit_username_login = findViewById(R.id.edit_username_login)
            edit_password_login = findViewById(R.id.edit_password_login)


            if (edit_username_login.text.toString() == Username && edit_password_login.text.toString() == Password) {


                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("username", edit_username_login.toString())

                    var editor = token.edit()
                    editor.putString("loginusername", edit_username_login.text.toString())
                    editor.commit()

                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "incorrect login or password", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun letsMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}