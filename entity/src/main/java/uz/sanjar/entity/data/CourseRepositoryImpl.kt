package uz.sanjar.entity.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.sanjar.common.CourseData
import uz.sanjar.common.CourseEntity
import uz.sanjar.entity.source.local.CourseDao
import uz.sanjar.entity.source.remote.CourseApi
import javax.inject.Inject

/**   Created by Sanjar Karimov 4:48 PM 3/15/2025   */

internal class CourseRepositoryImpl @Inject constructor(
    private val courseApi: CourseApi,
    private val courseDao: CourseDao,
) : CourseRepository {
    override fun getMergedCourses(): Flow<Result<List<CourseData>>> =
        combine(getCourses(), getFavouriteCourses()) { networkResult, favResult ->
            if (networkResult.isSuccess && favResult.isSuccess) {
                val networkCourses = networkResult.getOrNull() ?: emptyList()
                val favCourses = favResult.getOrNull() ?: emptyList()
                val favIds = favCourses.map { it.id }.toSet()
                Result.success(networkCourses.map { data ->
                    data.copy(hasLike = data.id in favIds)
                })
            }else {
                // Return an error if either result failed.
                Result.failure(
                    networkResult.exceptionOrNull() ?: favResult.exceptionOrNull()
                    ?: Exception("Unknown error")
                )
            }
        }.flowOn(Dispatchers.IO)
            .catch { e -> emit(Result.failure(e)) }

    override fun getCourses(): Flow<Result<List<CourseData>>> = flow {
        val response = courseApi.getCourses()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Result.success(it.courses))
            }
        }
    }.flowOn(Dispatchers.IO).catch { emit(Result.failure(it)) }

    override suspend fun insertCourse(courseEntity: CourseEntity): Result<Boolean> =
        try {
            courseDao.insertCourse(courseEntity)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun deleteFavourite(courseId: Int): Result<Boolean> = try {
        courseDao.removeCourse(courseId)
        Result.success(true)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun getFavouriteCourses(): Flow<Result<List<CourseEntity>>> =
        flow {
            courseDao.getCourses()
                .flowOn(Dispatchers.IO)
                .collect {
                    emit(Result.success(it))
                }
        }.flowOn(Dispatchers.IO)

}