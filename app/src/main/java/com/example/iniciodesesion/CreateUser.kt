package com.example.iniciodesesion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_user.*
import android.R.attr.password
import android.content.ContentValues.TAG
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth

class CreateUser : AppCompatActivity() {

    // asignamos la autenticacion
    private  lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        // la llamamos
        auth = FirebaseAuth.getInstance()

        // funcionalidad del boton registrar
        btnregistrar.setOnClickListener{
            // asignamos valores de entrada
            val Email = edtxtMailC.text.toString()
            val pass = edtxtContraC.text.toString()

            //nos aseguramos de que ningun valor de entrada quede vacio.
            if(Email.isBlank() || pass.isBlank()){
             Toast.makeText(this, "No deje ningun espacio vacio por favor", Toast.LENGTH_SHORT).show()
            }
            else{
                // asignamos la creacion de usuarios, en el caso de ser exitoso la activity se cierra
                    // sino da un mensaje de haber fallado
                auth.createUserWithEmailAndPassword(Email, pass)
                    .addOnCompleteListener(this){task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmail:success")
                            Toast.makeText(this, "Usuario creado", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}