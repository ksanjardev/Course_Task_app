package uz.sanjar.entity.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.sanjar.common.CourseEntity

/**   Created by Sanjar Karimov 7:38 AM 3/16/2025   */

@Database(entities = [CourseEntity::class], version = 1)
abstract class CourseDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}