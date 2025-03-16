package uz.sanjar.presenter

import androidx.lifecycle.LiveData
import uz.sanjar.common.CourseData

/**   Created by Sanjar Karimov 9:24 AM 3/16/2025   */
interface FavouriteViewModel {
    val errorMsg: LiveData<String>
    val courses: LiveData<List<CourseData>>
    fun onRemoveFav(id: Int)
}