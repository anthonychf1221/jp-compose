package com.anthonychaufrias.test1.ui.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {
    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> = _fullName

    private val _documentID = MutableLiveData<String>()
    val documentID: LiveData<String> = _documentID

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _company = MutableLiveData<String>()
    val company: LiveData<String> = _company

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled


    fun onSignUpValuesChanged(fullName: String, documentID: String, email: String){
        _fullName.value = fullName
        _documentID.value = documentID
        _email.value = email
        _isButtonEnabled.value = isValidName(fullName) && isValidDocumentID(documentID) && isValidEmail(email)
    }

    fun onCompanyChanged(value: String){
        _company.value = value
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
        Log.e("_fullName", _fullName.value.toString())
        Log.e("_documentID", _documentID.value.toString())
        Log.e("_email", _email.value.toString())
    }

}