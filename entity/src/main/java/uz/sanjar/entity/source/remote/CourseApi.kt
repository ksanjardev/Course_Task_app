package uz.sanjar.entity.source.remote

import retrofit2.Response
import retrofit2.http.GET
import uz.sanjar.common.CourseData
import uz.sanjar.entity.response.CourseResponse

/**   Created by Sanjar Karimov 4:35 PM 3/15/2025   */

internal interface CourseApi {
    @GET("u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun getCourses(): Response<CourseResponse>
}