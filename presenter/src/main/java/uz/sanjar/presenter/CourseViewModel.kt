package uz.sanjar.presenter

import androidx.lifecycle.LiveData
import uz.sanjar.common.CourseData

/**   Created by Sanjar Karimov 5:16 PM 3/15/2025   */
interface CourseViewModel {
    val errorMsg: LiveData<String>
    val courses: LiveData<List<CourseData>>
    val isAdded: LiveData<Boolean>
    fun onSortClick()
    fun onFavClick(courseData: CourseData)
}