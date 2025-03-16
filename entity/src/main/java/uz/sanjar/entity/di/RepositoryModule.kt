package uz.sanjar.entity.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.sanjar.entity.data.CourseRepository
import uz.sanjar.entity.data.CourseRepositoryImpl
import javax.inject.Singleton

/**   Created by Sanjar Karimov 4:52 PM 3/15/2025   */

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @[Binds Singleton]
    fun bindCourseRepository(impl: CourseRepositoryImpl): CourseRepository
}