package uz.sanjar.presenter.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.sanjar.common.CourseData
import uz.sanjar.common.toCourseEntity
import uz.sanjar.entity.data.CourseRepository
import uz.sanjar.presenter.CourseViewModel
import javax.inject.Inject

/**   Created by Sanjar Karimov 5:28 PM 3/15/2025   */

@HiltViewModel
class CourseViewModelImpl @Inject constructor(
    private val courseRepository: CourseRepository,
) : CourseViewModel, ViewModel() {
    override val errorMsg = MutableLiveData<String>()
    override val courses = MutableLiveData<List<CourseData>>()
    override val isAdded = MutableLiveData<Boolean>()

    init {
        courseRepository.getMergedCourses()
            .onEach { result ->
                result.onSuccess { courses.value = it }
                result.onFailure {
                    Log.d("TAG", "Error: $it ")
                    errorMsg.value = it.toString()
                }
            }.launchIn(viewModelScope)
    }

    override fun onSortClick() {
        courses.value = courses.value?.sortedByDescending { it.publishDate }
    }

    override fun onFavClick(courseData: CourseData) {
        viewModelScope.launch {
            if (isAdded.value != true) {
                val result = courseRepository.insertCourse(courseData.toCourseEntity())
                if (result.isSuccess) {
                    isAdded.value = true
                } else {
                    errorMsg.value = result.exceptionOrNull()?.message
                }
            } else {
                val result = courseRepository.deleteFavourite(courseData.id ?: -1)
                if (result.isSuccess) {
                    isAdded.value = false
                } else {
                    errorMsg.value = result.exceptionOrNull()?.message
                }
            }
        }
    }
}