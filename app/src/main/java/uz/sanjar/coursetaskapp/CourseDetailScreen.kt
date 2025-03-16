package uz.sanjar.coursetaskapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.sanjar.coursetaskapp.databinding.CourseDetailScreenBinding

@AndroidEntryPoint
class CourseDetailScreen : Fragment(R.layout.course_detail_screen) {
    private val binding: CourseDetailScreenBinding by viewBinding(CourseDetailScreenBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}