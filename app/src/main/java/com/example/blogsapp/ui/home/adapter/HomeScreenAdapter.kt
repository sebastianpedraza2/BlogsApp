package com.example.blogsapp.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogsapp.core.BaseViewHolder
import com.example.blogsapp.data.model.Post
import com.example.blogsapp.databinding.PostItemViewBinding

class HomeScreenAdapter(val postList: List<Post>) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            PostItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(itemBinding, parent.context)

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is PostViewHolder -> holder.bind(postList[position])
        }
    }

    override fun getItemCount(): Int = postList.size

    private inner class PostViewHolder(
        val binding: PostItemViewBinding,
        val context: Context
    ) : BaseViewHolder<Post>(binding.root) {
        override fun bind(item: Post) {
            Glide.with(context).load(item.post_img).centerCrop().into(binding.postImg)
            Glide.with(context).load(item.profile_picture).centerCrop().into(binding.profilePicture)
            binding.postTimestamp.text = "Hace 2 horas"
            binding.profileName.text = item.profile_name

        }
    }

}