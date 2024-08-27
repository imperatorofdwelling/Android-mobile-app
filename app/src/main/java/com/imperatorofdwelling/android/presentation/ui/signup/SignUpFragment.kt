package com.imperatorofdwelling.android.presentation.ui.signup

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }
}