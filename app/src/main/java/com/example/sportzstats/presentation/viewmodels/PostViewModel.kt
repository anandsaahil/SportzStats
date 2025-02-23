package com.example.sportzstats.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportzstats.domain.model.Post
import com.example.sportzstats.domain.usecases.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {
    private val _posts = MutableLiveData<Post?>()
    val posts: LiveData<Post?> get() = _posts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                _posts.value = getPostsUseCase()
            } catch (e: Exception) {
                _error.value = "Failed to load posts: ${e.message}"
            }
        }
    }
}
