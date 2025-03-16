package uz.sanjar.entity.data

import kotlinx.coroutines.flow.Flow
import uz.sanjar.common.CourseData
import uz.sanjar.common.CourseEntity

/**   Created by Sanjar Karimov 4:47 PM 3/15/2025   */

interface CourseRepository {
    fun getMergedCourses(): Flow<Result<List<CourseData>>>
    fun getCourses(): Flow<Result<List<CourseData>>>
    suspend fun insertCourse(courseEntity: CourseEntity): Result<Boolean>
    suspend fun deleteFavourite(courseId: Int) : Result<Boolean>
    fun getFavouriteCourses(): Flow<Result<List<CourseEntity>>>
}