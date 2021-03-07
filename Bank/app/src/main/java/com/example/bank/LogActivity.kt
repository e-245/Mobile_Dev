package com.example.bank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText


class LogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
    }

    fun login(view: View) {
        val input = findViewById<EditText>(R.id.inputPassword)
        val password = input.text.toString()
        if(password.hashCode() == 1477632){
            startActivity(Intent(this,MainActivity::class.java))
        }



    }
}