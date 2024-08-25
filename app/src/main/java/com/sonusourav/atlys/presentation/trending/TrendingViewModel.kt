package com.sonusourav.atlys.presentation.trending

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.sonusourav.atlys.utils.NetworkResult
import com.sonusourav.atlys.domain.usecases.UseCases
import com.sonusourav.atlys.utils.Constants
import com.sonusourav.atlys.data.model.movies.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class TrendingViewModel @Inject constructor(useCases: UseCases) : ViewModel() {

    private var _trendingMoviesList = mutableStateListOf<MovieItem>()
    val trendingMoviesList: List<MovieItem> = _trendingMoviesList

    private var _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _apiError = mutableStateOf(false)
    val apiError: State<Boolean> = _apiError

    init {

        viewModelScope.launch {
            useCases.trendingMoviesList.invoke(Constants.LANG, 1).collect {
                when (it) {

                    is NetworkResult.Success -> {

                        _trendingMoviesList.clear()
                        it.value.results?.forEach { result ->
                            _trendingMoviesList.add(result)
                        }
                        _isLoading.value = false

                    }

                    is NetworkResult.Failure -> {
                        _apiError.value = true
                        _isLoading.value = false
                    }

                    is NetworkResult.Loading -> {
                        _isLoading.value = true
                    }
                }

            }
        }
    }
}