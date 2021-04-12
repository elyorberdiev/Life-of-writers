package com.example.adiblarhayoti.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.adiblarhayoti.fragments.ItemFragment

class TabPagerAdapter(fragmentManager: FragmentManager, var categoryList: List<String>):FragmentPagerAdapter(fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    
    override fun getCount(): Int = categoryList.size

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ItemFragment.newInstance("Uzbek adabiyoti")
            }
            1 -> {
                ItemFragment.newInstance("Mumtoz adabiyot")
            }
            else -> {
                ItemFragment.newInstance("Jahon adabiyoti")
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return categoryList[position]
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }



}