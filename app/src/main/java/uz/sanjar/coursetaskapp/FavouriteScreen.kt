package uz.sanjar.coursetaskapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.sanjar.coursetaskapp.adapter.CourseAdapter
import uz.sanjar.coursetaskapp.databinding.FragmentFavouriteScreenBinding
import uz.sanjar.presenter.impl.FavouriteViewModelImpl

@AndroidEntryPoint
class FavouriteScreen : Fragment(R.layout.fragment_favourite_screen) {
    private val binding by viewBinding(FragmentFavouriteScreenBinding::bind)
    private val viewModel: FavouriteViewModelImpl by viewModels()
    private val courseAdapter = CourseAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rv.adapter = courseAdapter
        viewModel.courses.observe(viewLifecycleOwner) {
            Log.d("TTT", "onViewCreated: $it")
            courseAdapter.submitList(it)
        }
        viewModel.errorMsg.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        courseAdapter.onBookmarkClickListener = {
            viewModel.onRemoveFav(it.id ?: -1)
        }
    }
}