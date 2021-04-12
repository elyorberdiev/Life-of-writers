package com.example.adiblarhayoti.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomePagerAdapter(fragmentManager: FragmentManager,var fragmentList: List<Fragment>):FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }


}