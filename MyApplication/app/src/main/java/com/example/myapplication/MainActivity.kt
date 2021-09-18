package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_hello.setOnClickListener()
        {
            var nombre = editTextTextPersonName.text
            Toast.makeText(this, "Hola " + nombre, Toast.LENGTH_SHORT).show()
        }


    }
}