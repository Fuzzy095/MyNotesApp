package com.example.mynotesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //sets the layout file to activity_splash_screen
        setContentView(R.layout.activity_login)
        //hides the status bar when splash screen is displayed
        supportActionBar?.hide()

        val login = findViewById<Button>(R.id.loginButton)
        val signup = findViewById<TextView>(R.id.newUserTextContd)

        val emailTextView = findViewById<EditText>(R.id.loginEmailTextView)
        val passwordTextView = findViewById<EditText>(R.id.loginPasswordTextView)

        // Initialize Firebase Auth
        auth = Firebase.auth
        auth = FirebaseAuth.getInstance()

        login.setOnClickListener {
            val email = emailTextView.text.toString() //fetches email input of user
            val password = passwordTextView.text.toString() //fetches password input of user

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Log.i("NOTE_ME", "User successfully signed in") //create a log entry
                }
                else {
                    Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show() //create a toast
                    Log.e("NOTE_ME", "User not signed in") //create a log entry
                }
            }
        }

        //transfers user to SignupActivity
        signup.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}