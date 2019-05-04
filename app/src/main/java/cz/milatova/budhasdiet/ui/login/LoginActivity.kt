package cz.milatova.budhasdiet.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import cz.milatova.budhasdiet.R
import cz.milatova.budhasdiet.ui.login.LoginButtonState.*
import cz.milatova.budhasdiet.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var firebaseAuth: FirebaseAuth
    private var loginButtonState: LoginButtonState = LOGIN
    private lateinit var user: FirebaseUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        register_button.setOnClickListener {
            loginButton.text = resources.getString(R.string.action_register)
            register_button.visibility = View.GONE
            loginButtonState = REGISTER
        }

        loginButton.setOnClickListener {
            if (username.text.isNullOrEmpty()) {
                Toast.makeText(this, resources.getString(R.string.invalid_username), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.text.isNullOrEmpty()){
                Toast.makeText(this, resources.getString(R.string.invalid_password), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (loginButtonState == REGISTER) {
                Log.d("LUDMILA", "before create");
                createNewUser()
            }
            if (loginButtonState == LOGIN) {
                Log.d("LUDMILA", "before login");
                loginCurrentUser()
            }
        }
    }

    private fun createNewUser() {
        firebaseAuth.createUserWithEmailAndPassword(username.text.toString(), password.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun loginCurrentUser(){
        firebaseAuth.signInWithEmailAndPassword(username.text.toString(), password.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                val currentUser = firebaseAuth.currentUser
                var intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                // If sign in fails, display a message to the user.
                Log.w("LUDMILA", "signInWithEmail:failure", task.getException());
                Toast.makeText(this@LoginActivity, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
    }
}

enum class LoginButtonState {
    LOGIN, REGISTER
}

