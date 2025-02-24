package com.example.sportzstats.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportzstats.domain.model.Post
import com.example.sportzstats.domain.usecases.GetMatchDetailsTwoUseCase
import com.example.sportzstats.domain.usecases.GetMatchDetailsOneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getMatchDetailsOneUseCase: GetMatchDetailsOneUseCase,
    private val getMatchDetailsTwoUseCase: GetMatchDetailsTwoUseCase
) : ViewModel() {
    private val _posts = MutableLiveData<Post?>()
    val posts: LiveData<Post?> get() = _posts
    private var callApisRandom = 0
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private fun getMatchDetailsOne() {
        viewModelScope.launch {
            try {
                _posts.value = getMatchDetailsOneUseCase()
            } catch (e: Exception) {
                _error.value = "Failed to load posts: ${e.message}"
            }
        }
    }

    private fun getMatchDetailsTwo() {
        viewModelScope.launch {
            try {
                _posts.value = getMatchDetailsTwoUseCase()
            } catch (e: Exception) {
                _error.value = "Failed to load posts: ${e.message}"
            }
        }
    }

    //to verify both the api calls
    fun callNextApi() {
        if (callApisRandom % 2 == 0) {
            getMatchDetailsTwo()  // Call API Two on even counter
        } else {
            getMatchDetailsOne() // Call API One on odd counter
        }
        callApisRandom++ // Increment counter for the next time
    }
}
