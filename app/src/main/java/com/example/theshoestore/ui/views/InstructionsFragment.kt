package com.example.theshoestore.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.theshoestore.R
import com.example.theshoestore.databinding.FragmentInstructionsBinding
import com.example.theshoestore.databinding.FragmentWelcomeBinding


class instructionsFragment : Fragment() {
    private lateinit var binding : FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInstructionsBinding.inflate(inflater,container,false)
        binding.btnNext.setOnClickListener{ view ->
            view.findNavController().navigate(instructionsFragmentDirections.actionInstructionsFragmentToShoeFragment())
        }
        return  binding.root
    }



}