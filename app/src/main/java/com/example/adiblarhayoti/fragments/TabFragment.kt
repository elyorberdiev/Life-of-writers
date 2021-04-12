package com.example.adiblarhayoti.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.example.adiblarhayoti.R
import com.example.adiblarhayoti.sharedPreferences.MyShared
import com.example.adiblarhayoti.adapters.TabPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_tab.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class TabFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var root: View
    lateinit var categoryList: ArrayList<String>
    lateinit var myPagerAdapter: TabPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        root = inflater.inflate(R.layout.fragment_tab, container, false)
        MyShared.init(root.context)
        loadCategories()
        myPagerAdapter = TabPagerAdapter(childFragmentManager, categoryList)

        root.view_pager.adapter = myPagerAdapter
        root.tab_layout.setupWithViewPager(root.view_pager)

        setupTabItems()
        categoryList.size

        root.view_pager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

        root.tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val titleCategory = customView?.findViewById<TextView>(R.id.categoryName)
                val linearLayout = customView?.findViewById<LinearLayout>(R.id.tab_linear)
                titleCategory?.setTextColor(resources.getColor(R.color.teal_200))
                linearLayout?.setBackgroundColor(resources.getColor(R.color.appColor))
                linearLayout?.background = getDrawable(root.context, R.drawable.my_shape)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val titleCategory = customView?.findViewById<TextView>(R.id.categoryName)
                val linearLayout = customView?.findViewById<LinearLayout>(R.id.tab_linear)
                titleCategory?.setTextColor(resources.getColor(R.color.title))
                linearLayout?.background = getDrawable(root.context, R.drawable.item_background)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        root.float_search.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.searchFragment)
        }

        return root
    }

    private fun setupTabItems() {
        val tabCount: Int = root.tab_layout.tabCount
        for (i in 0 until tabCount) {
            val tab = root.tab_layout.getTabAt(i)
            val item = LayoutInflater.from(root.context).inflate(R.layout.tab_item, null, false)

            val title = item?.findViewById<TextView>(R.id.categoryName)
            val tab_linear = item?.findViewById<LinearLayout>(R.id.tab_linear)
            title?.text = categoryList[i]
            if (i == 0) {
                title?.setTextColor(resources.getColor(R.color.teal_200))
                tab_linear?.background = getDrawable(root.context,R.drawable.my_shape)
            } else {
                title?.setTextColor(resources.getColor(R.color.title))
                tab_linear?.background = getDrawable(root.context,R.drawable.item_background)

            }
            tab?.customView = item
        }
    }

    private fun loadCategories() {
        categoryList = ArrayList()
        if (MyShared.getLang() == "uz") {
            categoryList.add("O'zbek adabiyoti")
            categoryList.add("Mumtoz adabiyot")
            categoryList.add("Jahon adabiyoti")
        } else {
            categoryList.add("Ўзбек адабиёти")
            categoryList.add("Мумтоз адабиёт")
            categoryList.add("Жаҳон адабиёти")
        }


    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}