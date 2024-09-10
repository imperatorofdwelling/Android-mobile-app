package com.imperatorofdwelling.android.presentation.ui.sign_In

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = SignInFragment()
    }

    private val viewModel: SignInViewModel by viewModels()
    private lateinit var binding: FragmentSignInBinding

    private fun initTextChangedListeners() {
        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetEmailError()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetPasswordError()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun initObservers() {
        viewModel.errorPasswordInput.observe(viewLifecycleOwner) {
            binding.tilPassword.error = if (it) {
                getString(R.string.password_error)
            } else {
                null
            }
        }
        viewModel.errorEmailInput.observe(viewLifecycleOwner) {
            binding.tilEmail.error = if (it) {
                getString(R.string.email_is_incorrect)
            } else {
                null
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater)
        initSubmitListener()
        initObservers()
        initTextChangedListeners()
        setTextColor()
        return binding.root
    }

    private fun setTextColor() {
        binding.email.setTextColor(ContextCompat.getColor(requireContext(), R.color.placeholder))
        binding.password.setTextColor(ContextCompat.getColor(requireContext(), R.color.placeholder))
    }

    private fun initSubmitListener() {
        binding.submit.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            viewModel.signIn(email, password)
        }
    }
}