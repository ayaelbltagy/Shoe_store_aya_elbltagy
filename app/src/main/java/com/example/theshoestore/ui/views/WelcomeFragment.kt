package com.example.theshoestore.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.theshoestore.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // to show menu on this fragment
        //  setHasOptionsMenu(true)
        binding = FragmentWelcomeBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // to get bundle
        val bundle = WelcomeFragmentArgs.fromBundle(requireArguments())
        binding.welcomeTxt.setText("Hello:"+" "+bundle.userName)
        binding.btnNext.setOnClickListener{ view ->
            view.findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsView())
        }

    }
}