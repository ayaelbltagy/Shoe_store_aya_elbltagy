package com.example.theshoestore.ui.views

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.theshoestore.R
import com.example.theshoestore.ShoeModel
import com.example.theshoestore.databinding.FragmentShoeBinding
import com.example.theshoestore.databinding.ShoeOneItemBinding
import com.example.theshoestore.viewModels.ShoeViewModel
import androidx.lifecycle.Lifecycle

import androidx.annotation.NonNull

import android.view.MenuInflater
import androidx.core.view.MenuHost
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.theshoestore.helper.PreferenceHelper


class ShoeFragment : Fragment() {

    private lateinit var shoeViewModel: ShoeViewModel
    private lateinit var binding: FragmentShoeBinding
    private lateinit var helper: PreferenceHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        helper = PreferenceHelper(activity)
        // Inflate the layout for this fragment
        binding = FragmentShoeBinding.inflate(inflater, container, false)
        // object from view model
        shoeViewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]
       // binding.lifecycleOwner = this
        shoeViewModel.dataShoeList.observe(viewLifecycleOwner) { item ->
            shoeListView(item)
        }

        binding.lifecycleOwner = viewLifecycleOwner
        binding.addButton.setOnClickListener {
            view?.let { it1 ->
                Navigation.findNavController(it1)
                    .navigate(R.id.action_shoeFragment_to_shoeDetailsFragment)
            }

        }

        return binding.root
    }


    private fun shoeListView(item: List<ShoeModel>) {
        item.forEach {

            val bindingView = ShoeOneItemBinding.inflate(
                LayoutInflater.from(requireContext()),
                binding.shoeListLinearLayout,
                false
            )

            with(bindingView) {
                shoeCompany.text = it.shoeCompany
                shoeDescription.text = it.shoeDescription
                shoeName.text = it.shoeName
                shoeSize.text = it.shoeSize
            }
            binding.shoeListLinearLayout.addView(bindingView.root)

        }

    }

    // enable options menu in this fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    // inflate the menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // handle menu item selection
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item!!.itemId
        if(id == R.id.menu_item){
            if (!helper.getEmail().equals("") || !helper.getEmail().equals(null)) {
                helper.setEmail("")
                findNavController().navigate(R.id.action_shoeFragment_to_loginFragment)

            }
        }
        return super.onOptionsItemSelected(item)
    }
}