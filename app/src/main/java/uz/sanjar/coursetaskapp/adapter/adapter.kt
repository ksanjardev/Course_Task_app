package uz.sanjar.coursetaskapp.adapter

import android.annotation.SuppressLint
import android.util.Log
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import uz.sanjar.common.CourseData
import uz.sanjar.coursetaskapp.R
import uz.sanjar.coursetaskapp.databinding.ItemCourseBinding

/**   Created by Sanjar Karimov 6:05 PM 3/15/2025   */

@SuppressLint("SetTextI18n")
class CourseAdapter : ListDelegationAdapter<List<CourseData>>() {
    var onBookmarkClickListener: ((CourseData) -> Unit)? = null

    init {
        val adapterRef = this
        delegatesManager.addDelegate(adapterDelegateViewBinding<CourseData, CourseData, ItemCourseBinding>(
            viewBinding = { inflater, parent ->
                ItemCourseBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            },
        ) {

            bind {
                binding.courseRating.text = item.rate
                binding.courseDate.text = item.startDate
                binding.courseTitle.text = item.title
                binding.courseSubtitle.text = item.text
                binding.coursePrice.text = "${item.price} ₽"
                binding.bookmarkIcon.setImageResource(if (item.hasLike == true) R.drawable.ic_filled_bookmark else R.drawable.ic_bookmark)
                Log.d("TTT", "in adapter : ${item.hasLike}")
                binding.bookmarkIcon.setOnClickListener {
                    adapterRef.onBookmarkClickListener?.invoke(item)
                }
            }
        })
    }

    fun submitList(courses: List<CourseData>) {
        items = courses
        notifyDataSetChanged()
    }
}

