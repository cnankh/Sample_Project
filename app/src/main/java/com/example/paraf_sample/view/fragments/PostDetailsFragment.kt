package com.example.paraf_sample.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.paraf_sample.R
import com.example.paraf_sample.databinding.PostDetailsFragmentBinding
import com.example.paraf_sample.viewmodel.PostDetailsViewModel

class PostDetailsFragment : Fragment() {

    private lateinit var viewModel: PostDetailsViewModel
    private var postUuid: Int = 0
    private lateinit var binding: PostDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.post_details_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PostDetailsViewModel::class.java)
        arguments?.let {
            postUuid = PostDetailsFragmentArgs.fromBundle(it).postUuid
        }
        viewModel.fetch(postUuid)
        viewModelObserver()

    }

    private fun viewModelObserver() {
        viewModel.postLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { post ->
                binding.post = post
            }
        })
    }

}