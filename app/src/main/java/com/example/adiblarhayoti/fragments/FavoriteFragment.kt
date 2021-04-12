package com.example.adiblarhayoti.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.adiblarhayoti.R
import com.example.adiblarhayoti.adapters.ItemFragmentRvAdapter
import com.example.adiblarhayoti.db.AppDatabase
import com.example.adiblarhayoti.models.Writer
import kotlinx.android.synthetic.main.fragment_favorite.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FavoriteFragment : Fragment() {
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
    lateinit var itemFragmentRvAdapter: ItemFragmentRvAdapter
    lateinit var writerList:ArrayList<Writer>
    lateinit var writerss:ArrayList<Writer>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_favorite, container, false)
        val writerDao = AppDatabase.getDatabase(root.context).writerDao()
        writerList = ArrayList()
        writerss = ArrayList()
        val writers = writerDao.getWriters()
        writerList.addAll(writers)

        itemFragmentRvAdapter =
            ItemFragmentRvAdapter(object : ItemFragmentRvAdapter.OnItemClickListener {
                override fun onClick(writer: Writer) {
                    val bundle = Bundle()
                    bundle.putSerializable("data", writer)
                    Navigation.findNavController(root).navigate(R.id.writerFragment, bundle)
                }

            },writerList)
        root.favorite_rv.adapter = itemFragmentRvAdapter
        root.favorite_rv.adapter!!.notifyDataSetChanged()

        root.flo_search.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.searchFragment)
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        val writers = AppDatabase.getDatabase(root.context).writerDao().getWriters()
        writerss.addAll(writers)
        itemFragmentRvAdapter = ItemFragmentRvAdapter(object :ItemFragmentRvAdapter.OnItemClickListener{
            override fun onClick(writer: Writer) {
                val bundle = Bundle()
                bundle.putSerializable("data", writer)
                Navigation.findNavController(root).navigate(R.id.writerFragment, bundle)
            }

        },writerss)
        root.favorite_rv.adapter = itemFragmentRvAdapter
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}