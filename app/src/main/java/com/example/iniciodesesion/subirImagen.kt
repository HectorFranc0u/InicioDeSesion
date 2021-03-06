package com.example.iniciodesesion

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_subir_imagen.*
import java.io.IOException
import java.util.*

class subirImagen : AppCompatActivity() {

    // agregamos las variables necesarias
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    lateinit var imagePreview: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subir_imagen)

        // las llamamos

        imagePreview = findViewById(R.id.image_preview)

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        // se da funcionalidad al funcion que nos hace escoger una imagen.
        btn_choose_image.setOnClickListener{

            val intent = Intent()
            intent.type = "image/"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent,"Select picture"), PICK_IMAGE_REQUEST)

        }

        // agregamos funcionalidad al boton que hace subir la imagen.
        btn_upload_image.setOnClickListener{
            if(filePath != null){
                val ref = storageReference?.child("myImages/" + UUID.randomUUID().toString())
                val uploadTask = ref?.putFile(filePath!!)
                Toast.makeText(this, "Subido con exito", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Por favor suba una imagen", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK){
            if(data == null || data.data == null){
                return
            }
            filePath = data.data
            try{
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imagePreview.setImageBitmap(bitmap)
            } catch (e: IOException){
                e.printStackTrace()
            }
        }
    }
}