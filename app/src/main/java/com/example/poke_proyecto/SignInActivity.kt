package com.example.poke_proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {
    private lateinit var textName:TextView
    private lateinit var textEmail:TextView
    private lateinit var textPassword:TextView
    private lateinit var registerButton:Button
    private lateinit var progressBar:ProgressBar
    private lateinit var dbReference:DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        textName = findViewById(R.id.editTextTextPersonName2)
        textEmail = findViewById(R.id.emailText)
        textPassword = findViewById(R.id.passwordText)
        registerButton = findViewById(R.id.buttonRegister)
        progressBar = findViewById(R.id.progressBarRegister)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbReference = database.reference.child("User")

    }
    fun registerToLogin(view:View){
        println("ENTRO AL REGISTER")
        newAccount()
    }
    private fun newAccount(){
        val name:String = textName.text.toString()
        val email:String = textEmail.text.toString()
        val password:String = textPassword.text.toString()
        if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(name)){
            progressBar.visibility = View.VISIBLE
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){
                    task->
                    if(task.isComplete){
                        val user:FirebaseUser?= auth.currentUser
                        verifyEmail(user)

                        val userBD= dbReference.child(user!!.uid)
                        userBD.child("Name").setValue(name)
                        action()
                    }
                }
        }else{
            Toast.makeText(this,"Ingrese los datos correctamente",Toast.LENGTH_LONG).show()
        }
    }
    private fun action(){
        startActivity(Intent(this,LoginActivity::class.java))
    }
    private fun verifyEmail(user:FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this){
                task->
                if(task.isComplete){
                    Toast.makeText(this,"Correo Enviado Correctamente", Toast.LENGTH_SHORT,).show()
                }else{
                    Toast.makeText(this,"Error al enviar el correo de verificacion",Toast.LENGTH_LONG).show()
                }

            }
    }
}