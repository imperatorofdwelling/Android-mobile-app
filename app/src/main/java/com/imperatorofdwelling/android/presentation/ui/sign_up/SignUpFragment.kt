package com.imperatorofdwelling.android.presentation.ui.sign_up

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.imperatorofdwelling.android.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = SignUpFragment()
    }

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()


    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        initListeners()
        initTextChangedListeners()
        initObservers()
        return binding.root
    }

    private fun initListeners() {
        binding.submit.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val name = binding.name.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()
            val confirmPolicy = binding.confirmCheckBox.isChecked
            viewModel.signUp(name, email, password, confirmPassword, confirmPolicy)
        }
    }

    rivate fun initObservers() {
        viewModel.errorConfirmPolicy.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.please_confirm_the_policy), Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
        viewModel.errorEmailInput.observe(viewLifecycleOwner) {
            binding.tilEmail.error = if (it) {
                getString(R.string.email_is_incorrect)
            } else {
                null
            }
        }
        viewModel.errorPasswordInput.observe(viewLifecycleOwner) {
            binding.tilPassword.error = if (it) {
                getString(R.string.password_error)
            } else {
                null
            }
        }
        viewModel.errorIncorrectNameInput.observe(viewLifecycleOwner) {
            binding.tilName.error = if (it) {
                getString(R.string.username_can_only_contain_letters_and_numbers)
            } else {
                null
            }
        }

        viewModel.errorSizeNameInput.observe(viewLifecycleOwner) {
            binding.tilName.error = if (it) {
                getString(R.string.username_is_too_short)
            } else {
                null
            }
        }

        viewModel.errorPasswordEqualsInput.observe(viewLifecycleOwner) {
            binding.tilConfirmPassword.error = if (it) {
                getString(R.string.the_passwords_do_not_match)
            } else {
                null
            }
        }

    }

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
        binding.name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetSizeNameInputError()
                viewModel.resetCorrectNameInputError()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.confirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetPasswordsEqualInputError()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }*/
}