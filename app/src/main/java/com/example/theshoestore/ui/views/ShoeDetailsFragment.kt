package com.example.theshoestore.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.theshoestore.ShoeModel
import com.example.theshoestore.R
import com.example.theshoestore.databinding.FragmentShoeDetailsViewBinding
import com.example.theshoestore.viewModels.ShoeViewModel


class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsViewBinding
    private lateinit var shoeViewModel: ShoeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShoeDetailsViewBinding.inflate(inflater, container, false)
        binding.shoeModel = ShoeModel()
        shoeViewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]
        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnAdd.setOnClickListener {
            getDataFromView()
        }
        return binding.root
    }


    private fun getDataFromView() {

        val bindingData = binding.shoeModel
        val shoeName =  bindingData?.shoeName.toString()
        val shoeCompany = bindingData?.shoeCompany.toString()
        val shoeDescription = bindingData?.shoeDescription.toString()
        val shoeSize = bindingData?.shoeSize.toString()
        if (shoeName.equals("") || shoeSize.equals("") || shoeCompany.equals("") || shoeDescription.equals("")) {

            Toast.makeText(context, "Please Fill The Empty Fields", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "shoe added successfully", Toast.LENGTH_SHORT).show()
            shoeViewModel.onSave(shoeName, shoeSize, shoeCompany, shoeDescription)
            findNavController().navigate(R.id.action_shoeDetailsFragment_to_shoeFragment)
        }
    }

}