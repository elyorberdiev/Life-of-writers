package com.example.adiblarhayoti.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.navigation.Navigation
import com.example.adiblarhayoti.R
import com.example.adiblarhayoti.db.AppDatabase
import com.example.adiblarhayoti.models.Writer
import com.like.LikeButton
import com.like.OnLikeListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_writer.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WriterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WriterFragment : Fragment() {
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
    lateinit var root:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_writer, container, false)
        val writerDao = AppDatabase.getDatabase(root.context).writerDao()
        val writers = writerDao.getWriters()
        val writer = arguments?.getSerializable("data") as Writer
        for (i in writers.indices){
            root.like_button.isLiked = writers[i]==writer

        }
        root.writer_name.text = writer.name
        root.writer_year.text = "(${writer.year})"
        Picasso.get().load(writer.image).into(root.writer_image)
        root.writer_desc.text = writer.desc
        root.like_button.setOnLikeListener(object :OnLikeListener{
            override fun liked(likeButton: LikeButton?) {
                writerDao.insertWriter(writer)
                likeButton?.background = getDrawable(root.context,R.drawable.item_back_favorite2)
            }

            override fun unLiked(likeButton: LikeButton?) {
                writerDao.deleteWriter(writer)
                likeButton?.background = getDrawable(root.context,R.drawable.item_back_favorite)
            }

        })

        root.search_write.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.searchFragment)
        }

        root.back_write.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.homeFragment)
        }

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WriterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WriterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}