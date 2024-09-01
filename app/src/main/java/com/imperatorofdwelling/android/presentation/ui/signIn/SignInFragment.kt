package com.imperatorofdwelling.android.presentation.ui.signIn

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private val viewModel: SignInViewModel by viewModels()
    private lateinit var binding: FragmentSignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater)
        initListeners()
        return binding.root
    }

    private fun initListeners(){
        binding.submit.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            viewModel.signUp(email, password)
        }
    }
}