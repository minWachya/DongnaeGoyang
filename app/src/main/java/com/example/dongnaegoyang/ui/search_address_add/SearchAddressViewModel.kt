package com.example.dongnaegoyang.ui.search_address_add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongnaegoyang.data.remote.model.response.AddressResponse
import com.example.dongnaegoyang.data.remote.repository.AddressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchAddressViewModel @Inject constructor(
    private val repository: AddressRepository
): ViewModel() {
    private val _addressResponse = MutableLiveData<AddressResponse>()
    val addressResponse: LiveData<AddressResponse> = _addressResponse

    fun getAddressListResponse(query: String,
                               page: Int,
                               size: Int,
                               apikey: String) = viewModelScope.launch {
        kotlin.runCatching {
            repository.getAddressList(query, page, size, apikey)
        }.onSuccess {
            _addressResponse.value = it
            Log.d("mmm", it.toString())
        }.onFailure {
            Log.d("mmm", "get address add api fail ${it.message}")
        }
    }

}