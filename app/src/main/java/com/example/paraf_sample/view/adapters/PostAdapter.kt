package com.example.paraf_sample.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.paraf_sample.R
import com.example.paraf_sample.databinding.PostItemBinding
import com.example.paraf_sample.model.Post
import com.example.paraf_sample.view.OnPostClickController

class PostAdapter(private val postList: ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() ,OnPostClickController{

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Post>) {
        postList.clear()
        postList.addAll(newList)
        notifyDataSetChanged()

    }

    class PostViewHolder(var view: PostItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<PostItemBinding>(inflater, R.layout.post_item, parent, false)

        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.view.post = postList[position]
        holder.view.listener = this

    }


    override fun getItemCount() = postList.size
    override fun onPostClicked(view: View) {
        TODO("Not yet implemented")
    }
//    override fun onDogClicked(v: View) {
//        val _dogUuid = v.dogUuid_container.text.toString().toInt()
//        val action = ListFragmentDirections.actionListFragmentToDetailFragment()
//        action.dogUuid = _dogUuid
//        Navigation.findNavController(v).navigate(action)
//    }
}
