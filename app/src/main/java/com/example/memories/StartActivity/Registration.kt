package com.example.memories.StartActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.memories.MainActivity
import com.example.memories.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Registration : AppCompatActivity() {


    //FIREBASE
    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUser: DatabaseReference
    private var firebaseUserID: String = ""



    //ID
    lateinit var btn_login_in_registration: ConstraintLayout
    lateinit var btn_ok_reg: ConstraintLayout





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        btn_login_in_registration = findViewById(R.id.btn_login_in_registration)
        btn_ok_reg = findViewById(R.id.btn_ok_reg)

        btn_login_in_registration.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }



        mAuth = FirebaseAuth.getInstance()

        btn_ok_reg.setOnClickListener{
            registerUser()
        }


    }

    private fun registerUser() {

        val edit_username_login: String = findViewById<EditText>(R.id.edit_username_login).text.toString()
        val edit_email_login: String = findViewById<EditText>(R.id.edit_email_login).text.toString()
        val edit_password_login: String = findViewById<EditText>(R.id.edit_password_login).text.toString()

        if(edit_username_login == "") {
            Toast.makeText(this, "Enter the name", Toast.LENGTH_SHORT).show()
        } else if (edit_email_login == "") {
            Toast.makeText(this, "Enter the email", Toast.LENGTH_SHORT).show()
        } else if (edit_password_login == "") {
            Toast.makeText(this, "Enter the password", Toast.LENGTH_SHORT).show()
        } else
        {
            mAuth.createUserWithEmailAndPassword(edit_email_login, edit_password_login)
                .addOnCompleteListener {
                    task ->
                    if (task.isSuccessful)
                    {
                        firebaseUserID = mAuth.currentUser!!.uid
                        refUser = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)

                        val userHashMap = HashMap<String, Any>()
                        userHashMap["uid"] = firebaseUserID
                        userHashMap["username"] = edit_username_login
                        userHashMap["email"] = edit_email_login
                        userHashMap["password"] = edit_password_login

                        refUser.updateChildren(userHashMap)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful){
                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                            }

                    }
                    else
                    {
                        Toast.makeText(this, task.exception!!.toString(), Toast.LENGTH_LONG).show()
                    }
                }
        }
    }






    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

}