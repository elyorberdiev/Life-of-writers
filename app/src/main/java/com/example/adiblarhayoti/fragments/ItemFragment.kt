package com.example.adiblarhayoti.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.adiblarhayoti.R
import com.example.adiblarhayoti.adapters.ItemFragmentRvAdapter
import com.example.adiblarhayoti.db.AppDatabase
import com.example.adiblarhayoti.models.Writer
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_item.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


class ItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    lateinit var writerList:ArrayList<Writer>
    lateinit var itemFragmentRvAdapter: ItemFragmentRvAdapter
    lateinit var root:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_item, container, false)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference(param1!!)
        writerList = ArrayList()

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (child in snapshot.children) {
                    val user = child.getValue(Writer::class.java)
                    writerList.add(user!!)

                }

                root.itemFragment_rv.adapter?.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "onCancelled: ",error.toException())
            }

        })
        itemFragmentRvAdapter = ItemFragmentRvAdapter(object :ItemFragmentRvAdapter.OnItemClickListener{
            override fun onClick(writer: Writer) {
                val bundle = Bundle()
                bundle.putSerializable("data",writer)
                Navigation.findNavController(root).navigate(R.id.writerFragment,bundle)
            } },writerList)
        root.itemFragment_rv.adapter = itemFragmentRvAdapter


        return root
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}