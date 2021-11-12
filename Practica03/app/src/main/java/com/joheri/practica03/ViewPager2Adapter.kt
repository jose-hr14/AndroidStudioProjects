package com.joheri.practica03

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager2Adapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    //Devuelve el número de pestañas añadidas al viewPager
    override fun getItemCount(): Int {
        return mFragmentList.size
    }

    //Se crea el fragmento de la posición especificada
    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

    //Añade el fragmento a la pestaña
    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    //Devuelve el título de la pestaña
    fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }
}