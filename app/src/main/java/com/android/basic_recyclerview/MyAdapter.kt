package com.android.basic_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.basic_recyclerview.databinding.ItemRecyclerviewBinding
import com.android.basic_recyclerview.databinding.ItemTitleBinding

class MyAdapter(val mItems: MutableList<DogItems>) : RecyclerView.Adapter<ViewHolder>() {
    companion object {
        private const val VIEW_TYPE_TITLE = 1
        private const val VIEW_TYPE_DOGS = 2
    }

    inner class TitleViewHolder(binding: ItemTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.tvAgetitle
    }

    inner class DogViewHolder(binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.iconItem
        val name = binding.tvName
        val age = binding.tvAge
    }

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_TITLE -> {
                val binding =
                    ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TitleViewHolder(binding)
            }

            else -> {
                val binding = ItemRecyclerviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                DogViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item = mItems[position]) {
            is DogItems.MyTitle -> {
                (holder as TitleViewHolder).title.text = "${item.age} 살"
            }

            is DogItems.MyItem -> {
                (holder as DogViewHolder).name.text = item.aName
                with(holder) {
                    age.text = item.aAge
                    iconImageView.setImageResource(item.aIcon)
                    itemView.setOnClickListener {
                        itemClick?.onClick(it, position)
                    }
                }

            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (mItems[position]) {
            is DogItems.MyTitle -> VIEW_TYPE_TITLE
            is DogItems.MyItem -> VIEW_TYPE_DOGS
        }
    }
}