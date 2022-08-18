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


class ShoeFragment : Fragment(), MenuProvider {

    private lateinit var shoeViewModel: ShoeViewModel
    private lateinit var binding: FragmentShoeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShoeBinding.inflate(inflater, container, false)
        // object from view model
        shoeViewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]
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
    
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        // Add menu items here
        menuInflater.inflate(R.menu.menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_item -> {
                Toast.makeText(
                    activity, "hello", Toast.LENGTH_LONG
                ).show()
                true
            }
            else -> false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // The usage of an interface lets you inject your own implementation
//        val menuHost: MenuHost = requireHost() as MenuHost
//        menuHost.addMenuProvider(object : MenuProvider {
//            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//                // Add menu items here
//                menuInflater.inflate(R.menu.menu, menu)
//            }
//            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//                view.findNavController().navigate(R.id.action_shoeFragment_to_loginFragment)
//                return true
//            }
//        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}