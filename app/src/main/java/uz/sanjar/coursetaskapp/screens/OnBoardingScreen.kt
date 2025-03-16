package uz.sanjar.coursetaskapp.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.sanjar.coursetaskapp.R
import uz.sanjar.coursetaskapp.databinding.OnboardingScreenBinding

/**   Created by Sanjar Karimov 7:29 AM 3/14/2025   */
@AndroidEntryPoint
class OnBoardingScreen : Fragment(R.layout.onboarding_screen) {
    private val binding: OnboardingScreenBinding by viewBinding(OnboardingScreenBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.nextBtn.setOnClickListener {
            findNavController().navigate(OnBoardingScreenDirections.actionOnBoardingScreenToLoginScreen())
        }
    }
}