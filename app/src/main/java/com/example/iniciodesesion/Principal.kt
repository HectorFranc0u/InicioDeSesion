package com.example.iniciodesesion

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_principal.*

class Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        // damos funcionalidad a los botones de los contactos del menu para llevarnos al telefono y agregar dichos numeros
        btnContacto1.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + "77220022")
            startActivity(dialIntent)
        }

        bntContacto2.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + "22778899")
            startActivity(dialIntent)
        }

        btnContacto3.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + "69690022")
            startActivity(dialIntent)
        }

        // boton para acceder a la activity de subir imagenes
        btnImage.setOnClickListener{
            val i = Intent(this,subirImagen::class.java)
            startActivity(i)
        }
    }
}