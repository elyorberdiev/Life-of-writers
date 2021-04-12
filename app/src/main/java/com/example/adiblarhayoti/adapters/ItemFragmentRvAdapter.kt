package com.example.adiblarhayoti.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.adiblarhayoti.R
import com.example.adiblarhayoti.db.AppDatabase
import com.example.adiblarhayoti.models.Writer
import com.like.LikeButton
import com.like.OnLikeListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_writer.view.*

class ItemFragmentRvAdapter(var listener: OnItemClickListener, var writerList: ArrayList<Writer>) :
    RecyclerView.Adapter<ItemFragmentRvAdapter.Vh>() {

    inner class Vh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(writer: Writer, position: Int) {
            val writerDao = AppDatabase.getDatabase(itemView.context).writerDao()

            itemView.writer_name.text = writer.name
            Picasso.get().load(writer.image).into(itemView.writer_img)
            itemView.writer_birthDeath.text = writer.year
            itemView.writer_save.isLiked = writer.favorite!!
            if (writer.favorite!!) {
                itemView.writer_save.background =
                    getDrawable(itemView.context, R.drawable.item_back_favorite2)
            } else {
                getDrawable(itemView.context, R.drawable.item_back_favorite)
            }

            itemView.writer_save.setOnLikeListener(object : OnLikeListener {
                override fun liked(likeButton: LikeButton?) {
                    writer.favorite = true
                    writerDao.insertWriter(writer)

                    likeButton?.background =
                        getDrawable(itemView.context, R.drawable.item_back_favorite2)
                }

                override fun unLiked(likeButton: LikeButton?) {
                    likeButton?.background =
                        getDrawable(itemView.context, R.drawable.item_back_favorite)

                    writerDao.deleteWriter(writerList[position])
                    writerList.remove(writerList[position])
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position,writerList.size)

                }

            })
            itemView.setOnClickListener {
                listener.onClick(writer)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(LayoutInflater.from(parent.context).inflate(R.layout.item_writer, parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(writerList[position], position)

    }

    override fun getItemCount(): Int = writerList.size

    interface OnItemClickListener {
        fun onClick(writer: Writer)

    }

}