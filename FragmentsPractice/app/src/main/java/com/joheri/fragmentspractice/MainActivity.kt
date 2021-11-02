package com.joheri.fragmentspractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.joheri.fragmentspractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPager2Adapter(supportFragmentManager, lifecycle)

        adapter.addFragment(FragmentoCalculo(), "Calculo")
        adapter.addFragment(FragmentoCalculo(), "Calculo copia")

        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2){tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }
}