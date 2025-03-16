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
import uz.sanjar.common.toCourseData
import uz.sanjar.entity.data.CourseRepository
import uz.sanjar.presenter.FavouriteViewModel
import javax.inject.Inject

/**   Created by Sanjar Karimov 9:25 AM 3/16/2025   */
@HiltViewModel
class FavouriteViewModelImpl @Inject constructor(
    private val courseRepository: CourseRepository,
) : FavouriteViewModel, ViewModel() {
    override val errorMsg = MutableLiveData<String>()
    override val courses = MutableLiveData<List<CourseData>>()
    override fun onRemoveFav(id: Int) {
        viewModelScope.launch {
            courseRepository.deleteFavourite(id)
        }
    }

    init {
        courseRepository.getFavouriteCourses()
            .onEach { result ->
                result.onSuccess {
                    Log.d("TTT", "onViewCreated: $it")
                    courses.value = it.map { data ->
                        Log.d("TTT", "CourseData: ${data.hasLike}")
                        data.toCourseData()
                    }
                }
                result.onFailure { errorMsg.value = it.toString() }
            }.launchIn(viewModelScope)
    }
}