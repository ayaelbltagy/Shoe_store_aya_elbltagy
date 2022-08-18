package com.example.theshoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.theshoestore.databinding.FragmentSplashBinding
import com.example.theshoestore.helper.PreferenceHelper
import com.example.theshoestore.ui.views.WelcomeFragmentDirections


class SplashFragment : Fragment() {


    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var helper = PreferenceHelper(activity)

        if (helper.getEmail().equals("") || helper.getEmail() == null) {
            view.findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginView())

        } else {
            view.findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToShoeFragment())


        }
    }

}