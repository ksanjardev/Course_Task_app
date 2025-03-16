package uz.sanjar.coursetaskapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.sanjar.coursetaskapp.adapter.CourseAdapter
import uz.sanjar.coursetaskapp.databinding.HomeScreenBinding
import uz.sanjar.presenter.impl.CourseViewModelImpl

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.home_screen) {
    private val binding: HomeScreenBinding by viewBinding(HomeScreenBinding::bind)
    private val viewModel: CourseViewModelImpl by activityViewModels()
    private val courseAdapter = CourseAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.sortCourses.isEnabled = false
        binding.sortCourses.setTextColor(
            ContextCompat.getColor(requireContext(), R.color.disabled_text)
        )
        observeLivedata()
        setClickListeners()
    }

    private fun observeLivedata() {
        binding.rv.adapter = courseAdapter

        viewModel.courses.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it.isNullOrEmpty()) View.VISIBLE else View.GONE

            courseAdapter.submitList(it)
            val isEnabled = !it.isNullOrEmpty()
            binding.sortCourses.isEnabled = isEnabled
            binding.sortCourses.setTextColor(
                if (isEnabled) requireContext().getColor(R.color.enabled_text)
                else requireContext().getColor(R.color.disabled_text)
            )
        }
        viewModel.errorMsg.observe(viewLifecycleOwner) {
            Log.d("TTT", "observeLivedata: $it")
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setClickListeners() {
        binding.sortCourses.setOnClickListener {
            viewModel.onSortClick()
        }
        courseAdapter.onBookmarkClickListener = {
            viewModel.onFavClick(it)
        }
    }
}