package com.example.adiblarhayoti.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.adiblarhayoti.R
import com.example.adiblarhayoti.adapters.HomePagerAdapter
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener
import kotlinx.android.synthetic.main.fragment_home.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
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

    private lateinit var root:View
    lateinit var homePagerAdapter: HomePagerAdapter
    lateinit var fragmentList: ArrayList<Fragment>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_home, container, false)
//        root.home_view_pager.offscreenPageLimit = 2
        fragmentList = ArrayList()
        fragmentList.add(TabFragment())
        fragmentList.add(FavoriteFragment())
        fragmentList.add(SettingsFragment())
        homePagerAdapter = HomePagerAdapter(childFragmentManager,fragmentList)
        root.home_view_pager.adapter = homePagerAdapter
        root.bottom_navigation_view_linear.setNavigationChangeListener(object :BubbleNavigationChangeListener{
            override fun onNavigationChanged(view: View?, position: Int) {
                root.home_view_pager.currentItem = position

            }

        })
        root.home_view_pager.setOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                root.bottom_navigation_view_linear.setCurrentActiveItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

        return root

    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}