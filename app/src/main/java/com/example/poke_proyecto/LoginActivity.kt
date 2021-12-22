package com.example.poke_proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBoton:Button
    private lateinit var progressbar:ProgressBar
    private lateinit var textEmail:EditText
    private lateinit var textPassword:EditText
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginBoton = findViewById(R.id.buttonLogin)
        textEmail = findViewById(R.id.editTextTextEmailAddress)
        textPassword = findViewById(R.id.editTextTextPassword)
        progressbar = findViewById(R.id.progressBar)
        auth = FirebaseAuth.getInstance()
    }
    fun register(view:View){
        val intent = Intent(this,SignInActivity::class.java)
        startActivity(intent)
    }
    fun login(view:View){
        loginUser()
    }
    private fun loginUser(){
        var textMailVal:String = textEmail?.text.toString()
        var textPasswordVal:String = textPassword?.text.toString()
        if (!TextUtils.isEmpty(textMailVal)&&!TextUtils.isEmpty(textPasswordVal)){
            progressbar.visibility=View.VISIBLE
            auth.signInWithEmailAndPassword(textMailVal,textPasswordVal)
                .addOnCompleteListener(this){
                    task->
                    if(task.isSuccessful){
                        progressbar.visibility=View.GONE
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        progressbar.visibility=View.GONE
                        Toast.makeText(this,"Error al intentar ingresar", Toast.LENGTH_SHORT).show()
                    }
                }

        }
        else {
            Toast.makeText(this,"Debe Ingresar Los Valores Indicados", Toast.LENGTH_SHORT).show()

        }
    }
}