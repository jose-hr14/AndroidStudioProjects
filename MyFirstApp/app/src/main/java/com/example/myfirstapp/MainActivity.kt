package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myfirstapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity()
{
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.button.setOnClickListener()
        {
            decirHola()
        }
    }

    private fun decirHola()
    {
        if (mainBinding.editTextTextPersonName.text.toString().isEmpty() || mainBinding.editTextTextPersonName.toString() == "")
            Toast.makeText(this, "Necesito que me des el nombre", Toast.LENGTH_SHORT).show()
        else
        {
            Toast.makeText(this, "Hola ${mainBinding.editTextTextPersonName.text.toString()}", Toast.LENGTH_SHORT).show()
        }

    }

}