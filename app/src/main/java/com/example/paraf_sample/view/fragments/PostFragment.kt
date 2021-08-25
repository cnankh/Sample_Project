package com.example.paraf_sample.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paraf_sample.R
import com.example.paraf_sample.view.adapters.PostAdapter
import com.example.paraf_sample.viewmodel.LoginViewModel
import com.example.paraf_sample.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.post_fragment.*

class PostFragment : Fragment() {

    private lateinit var viewModel: PostViewModel
    private var mAdapter = PostAdapter(arrayListOf())
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        viewModel.fetch()

        mLayoutManager = LinearLayoutManager(context)

        post_recycler_view.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
        }

        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.posts.observe(viewLifecycleOwner, Observer { posts ->
            posts?.let {
                mAdapter.updateList(posts)
            }

        })
    }

}