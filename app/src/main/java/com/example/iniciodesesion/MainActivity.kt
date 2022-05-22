package com.example.iniciodesesion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // asignamos la autenticacion de firebase
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Llamamos a la autenticacion de firebase
        auth = FirebaseAuth.getInstance()

        // damos la funcionalidad al boton de ingresar
        btnIngresar.setOnClickListener {

            //declaramos las variables de el EMAIL y la contrasena
            val Email = edtxtMail.text.toString()
            val Pass = edtxtPass.text.toString()

            // asignamos una advertencia para evitar que los espacios queden vacios
            if(Email.isBlank() || Email.isBlank()){
                Toast.makeText(this, "No deje ningun espacio en blanco.", Toast.LENGTH_SHORT).show()
            }
            // asignamos la funcionalidad al boton en el caso de que los espacios no esten vacios
            else{
                auth.signInWithEmailAndPassword(Email, Pass).addOnCompleteListener(this) {
                    // agregamos un mensaje para dar a entender al usuario si el ingreso fue exitoso o si fue denegado
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Acceso Autorizado!", Toast.LENGTH_SHORT).show()

                        val e = Intent(this, Principal::class.java)
                        startActivity(e)

                        finish()
                    } else {
                        Toast.makeText(this, "Acceso no autorizado! ", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // boton para acceder a la activitie para crear una cuenta
        txtCrear.setOnClickListener {
            val i = Intent(this, CreateUser::class.java)
            startActivity(i)
        }
    }
}

