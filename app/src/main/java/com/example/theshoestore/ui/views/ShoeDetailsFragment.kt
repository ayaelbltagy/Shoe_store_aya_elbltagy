package com.example.theshoestore.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.theshoestore.R
import com.example.theshoestore.databinding.BindShoeDetailsBinding
import com.example.theshoestore.viewModels.ShoeViewModel


class ShoeDetailsFragment : Fragment() {

    private lateinit var shoeViewModel: ShoeViewModel
    private lateinit var binding: BindShoeDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = BindShoeDetailsBinding.inflate(inflater, container, false)
        shoeViewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]
        binding.shoeModel = shoeViewModel
        binding.lifecycleOwner = this
        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnAdd.setOnClickListener {
            getDataFromView()
        }
        return binding.root
    }


    private fun getDataFromView() {

        if (binding.txtName.text.toString().equals("") || binding.txtSize.text.toString()
                .equals("") || binding.txtCompany.text.toString()
                .equals("") || binding.txtDesc.text.toString().equals("")
        ) {

            Toast.makeText(context, "Please Fill The Empty Fields", Toast.LENGTH_SHORT).show()
        } else {
            shoeViewModel.onSave()

            Toast.makeText(context, "shoe added successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_shoeDetailsFragment_to_shoeFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        // to clear data that still found on views in second time
        binding.txtName.setText("")
        binding.txtSize.setText("")
        binding.txtCompany.setText("")
        binding.txtDesc.setText("")

    }

}