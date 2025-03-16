package uz.sanjar.coursetaskapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.sanjar.coursetaskapp.databinding.LoginScreenBinding
import uz.sanjar.presenter.impl.LoginViewModelImpl


class LoginScreen : Fragment(R.layout.login_screen) {
    private val binding: LoginScreenBinding by viewBinding(LoginScreenBinding::bind)
    private val viewModel: LoginViewModelImpl by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.loginBtn.isEnabled = false
        binding.okBtn.setOnClickListener { viewModel.onOKClick() }
        binding.vkBtn.setOnClickListener { viewModel.onVkCLick() }
        viewModel.navigatorUrlEvent.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                startActivity(intent)
            }
        }
        setupValidation()
        binding.loginBtn.setOnClickListener {
            val sharedPref = requireContext().getSharedPreferences("app_pref", MODE_PRIVATE)
            sharedPref.edit().putBoolean("is_first_time", false).apply()

            findNavController().navigate(LoginScreenDirections.actionLoginScreenToNavHome())

        }
    }

    private fun setupValidation() {
        binding.email.addTextChangedListener(afterTextChanged = { _ -> validateInputs() })
        binding.passwordd.addTextChangedListener(afterTextChanged = { _ -> validateInputs() })
    }

    private fun validateInputs() {
        val email = binding.email.text.toString().trim()
        val password = binding.passwordd.text.toString()
        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex()
        val isValid = email.matches(emailRegex) && password.isNotEmpty()

        binding.loginBtn.isEnabled = isValid

    }
}