package com.example.theshoestore.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.theshoestore.databinding.FragmentInstructionsBinding

class InstructionsView : Fragment() {
    private lateinit var binding : FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInstructionsBinding.inflate(inflater,container,false)
        binding.btnNext.setOnClickListener{ view ->
            view.findNavController().navigate(InstructionsViewDirections.actionInstructionsViewToShoeFragment())
        }
        return  binding.root
    }

}