package com.joheri.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joheri.examen.databinding.ActivitySecondBinding
import com.joheri.examen.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    lateinit var binding: ActivityThirdBinding
    var titulo: String? = null
    var autor: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}