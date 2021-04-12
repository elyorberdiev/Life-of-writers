package com.example.adiblarhayoti.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.adiblarhayoti.R
import com.example.adiblarhayoti.models.Writer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_writer.view.*

class ItemFragmentRvAdapter2(var listener:OnItemClickListener) : ListAdapter<Writer, ItemFragmentRvAdapter2.VH>(WriterDiffUtil()) {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(writer: Writer) {

            itemView.writer_name.text = writer.name
            Picasso.get().load(writer.image).into(itemView.writer_img)
            itemView.writer_birthDeath.text = writer.year
            itemView.writer_save.isLiked = writer.favorite!!

            itemView.setOnClickListener {
                listener.onClick(writer)
            }
        }
    }

    class WriterDiffUtil : DiffUtil.ItemCallback<Writer>() {
        override fun areItemsTheSame(oldItem: Writer, newItem: Writer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Writer, newItem: Writer): Boolean {
            return newItem.equals(oldItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_writer, parent, false))

    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position))
    }
    interface OnItemClickListener {
        fun onClick(writer: Writer)

    }
}