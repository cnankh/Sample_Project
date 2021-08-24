package com.example.paraf_sample.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.paraf_sample.R
import com.example.paraf_sample.util.SharedPreferencesHelper

class SplashFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token = SharedPreferencesHelper.buildHelper(requireContext()).getToken();
        Log.d("tag splash token", token.toString())
        if (token.isNullOrBlank()) {
            val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
            Navigation.findNavController(view).navigate(action)
            return
        }
        val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
        Navigation.findNavController(view).navigate(action)

    }

}