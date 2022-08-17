package com.example.theshoestore.ui.views

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.theshoestore.databinding.FragmentLoginBinding
import com.example.theshoestore.helper.PreferenceHelper
import java.util.regex.Pattern

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private var userEmail = ""
    private var password = ""
    private lateinit var helper :PreferenceHelper
    private val emailPattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+", Pattern.CASE_INSENSITIVE
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
//        binding.login.setOnClickListener(
//           Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_welcomeFragment)
//        )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        helper = PreferenceHelper(context)
        // get email from view
        binding.email.apply {
            afterTextChanged {
                userEmail = binding.email.text.toString()
            }
        }
        // get password from view
        binding.password.apply {
            afterTextChanged {
                password = binding.password.text.toString().trim()
            }
        }

        binding.btnReset.setOnClickListener {
            // clearing email and password edit text views on reset button click
            binding.email.setText("")
            binding.password.setText("")
        }

        binding.btnSubmit.setOnClickListener(View.OnClickListener { view ->
            view.hideKeyboard()
            // passing email ( as a save arg ) to welcome fragment
            if (userEmail.length < 0 || userEmail.equals("") || password.length < 0 || password.equals("")) {
                Toast.makeText(context, "please enter email and password", Toast.LENGTH_LONG).show()
            } else {
                if (emailPattern.matcher(binding.email.text.toString()).matches()) {
                    helper.setEmail(binding.email.text.toString())
                    view.findNavController()
                        .navigate(
                            LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(
                                userEmail
                            )
                        )
                } else {
                    Toast.makeText(context, "please enter correct email", Toast.LENGTH_LONG).show()

                }
            }
        })
    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onResume() {
        super.onResume()
        // clearing email and password edit text views
        binding.email.setText("")
        binding.password.setText("")
    }
}


/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}




