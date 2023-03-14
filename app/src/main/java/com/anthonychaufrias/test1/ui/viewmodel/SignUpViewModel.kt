package com.anthonychaufrias.test1.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonychaufrias.test1.domain.SignUpUseCase
import com.anthonychaufrias.test1.domain.model.SignUp
import com.anthonychaufrias.test1.util.Resource
import com.anthonychaufrias.test1.util.SuccessMessageCode
import com.anthonychaufrias.test1.util.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel(){
    private val _signUpModel = MutableLiveData<SignUp>()
    val signUpModel: LiveData<SignUp> = _signUpModel

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    private val _result = MutableLiveData<UIText?>()
    val result: LiveData<UIText?> = _result

    private val _isStillLoading = MutableLiveData<Boolean>()
    val isStillLoading: LiveData<Boolean> = _isStillLoading

    fun onSignUpValuesChanged(fullName: String, documentID: String,
                              email: String, company: String, countryID: Int){
        _signUpModel.value = SignUp(fullName, documentID, email, company, countryID)

        _isButtonEnabled.value = isValidName(fullName) &&
                                 isValidDocumentID(documentID) &&
                                 isValidEmail(email)
    }


    private fun isValidEmail(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidDocumentID(documentID: String): Boolean{
        return documentID.length == 8
    }

    private fun isValidName(fullName: String): Boolean{
        return fullName.isNotEmpty()
    }

    fun onSignUp(){
        viewModelScope.launch {
            _isStillLoading.value = true
            val resource = signUpUseCase(_signUpModel.value ?: SignUp.emptyObject())
            when(resource){
                is Resource.Success<*> -> {
                    //_signUpModel.value = (resource.data as SignUpResponse).result
                    //_success.value = (resource.data as SignUpResponse).message
                    _result.value = UIText.SuccessMessageCodeResource(code = SuccessMessageCode.SIGNUPSUCCESSFULLY)
                }
                is Resource.Error<*> -> {
                    _result.value = resource.message
                }
            }
            _isStillLoading.value = false
        }
    }

}