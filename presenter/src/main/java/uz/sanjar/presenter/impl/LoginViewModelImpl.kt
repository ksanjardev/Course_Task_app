package uz.sanjar.presenter.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.sanjar.presenter.LoginViewModel
import javax.inject.Inject

/**   Created by Sanjar Karimov 10:48 AM 3/16/2025   */

@HiltViewModel
class LoginViewModelImpl @Inject constructor(): LoginViewModel, ViewModel() {
    override val navigatorUrlEvent = MutableLiveData<String>()

    override fun onVkCLick() {
        navigatorUrlEvent.value = "https://vk.com/"
    }

    override fun onOKClick() {
        navigatorUrlEvent.value = "https://ok.ru/"
    }

}