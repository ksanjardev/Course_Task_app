package uz.sanjar.entity.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.sanjar.entity.source.local.CourseDao
import uz.sanjar.entity.source.local.CourseDatabase
import javax.inject.Singleton

/**   Created by Sanjar Karimov 7:41 AM 3/16/2025   */

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @[Provides Singleton]
    fun provideCourseDatabase(@ApplicationContext context: Context): CourseDatabase =
        Room.databaseBuilder(context, CourseDatabase::class.java, "CourseDatabase").build()

    @[Provides Singleton]
    fun provideCourseDao(database: CourseDatabase): CourseDao = database.courseDao()
}