package com.example.adduserinteractivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<EditText>(R.id.nickname_edit)

        añadirNombre()
    }

    private fun añadirNombre()
    {
        val nickname_edit = findViewById<EditText>(R.id.nickname_edit)
        val done_button = findViewById<Button>(R.id.done_button)
        val nickname_text = findViewById<TextView>(R.id.nickname_text)
        done_button.setOnClickListener()
        {
            nickname_text.text = nickname_edit.text
            nickname_edit.visibility = View.GONE
            nickname_text.visibility = View.VISIBLE
        }
        nickname_text.setOnClickListener()
        {
            nickname_text.visibility = View.GONE
            nickname_edit.text = null
            nickname_edit.visibility = View.VISIBLE

        }

    }
}