package uz.sanjar.entity.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.sanjar.common.CourseEntity

/**   Created by Sanjar Karimov 7:39 AM 3/16/2025   */

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(courseEntity: CourseEntity)

    @Query("DELETE FROM CourseEntity WHERE id = :courseId")
    suspend fun removeCourse(courseId: Int)

    @Query("SELECT * FROM CourseEntity")
    fun getCourses(): Flow<List<CourseEntity>>
}