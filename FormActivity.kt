package com.example.user_info

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.user_info.databinding.ActivityFormBinding
import com.example.user_info.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class FormActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFormBinding
    private lateinit var database : DatabaseReference




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.submit.setOnClickListener {

            val userName = binding.userName.text.toString()
            val userEmail = binding.userEmail.text.toString()
            val userNumber = binding.userNumber.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val user = User(userName, userEmail, userNumber)
            database.child(userName).setValue(user).addOnSuccessListener {

                Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to Store Data", Toast.LENGTH_SHORT).show()
            }

        }

    }
}