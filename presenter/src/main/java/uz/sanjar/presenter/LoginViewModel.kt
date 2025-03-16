package uz.sanjar.presenter

import androidx.lifecycle.LiveData

/**   Created by Sanjar Karimov 10:48 AM 3/16/2025   */
interface LoginViewModel {
    val navigatorUrlEvent: LiveData<String>
    fun onVkCLick()
    fun onOKClick()
}