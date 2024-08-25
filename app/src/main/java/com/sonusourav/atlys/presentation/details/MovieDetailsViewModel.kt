package com.sonusourav.atlys.presentation.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.SavedStateHandle
import com.sonusourav.atlys.data.model.details.MovieDetailsResponse
import com.sonusourav.atlys.domain.usecases.UseCases
import com.sonusourav.atlys.utils.Constants
import com.sonusourav.atlys.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    useCases: UseCases, savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _movieDetailsResponse: MutableState<MovieDetailsResponse> =
        mutableStateOf(MovieDetailsResponse())
    val movieDetailsResponse: State<MovieDetailsResponse> = _movieDetailsResponse

    private val _apiError = mutableStateOf(false)
    val apiError: State<Boolean> = _apiError

    private var _isLoading = mutableStateMapOf<Int, Boolean>()
    val isLoading: Map<Int, Boolean> = _isLoading

    init {
        savedStateHandle.get<String>("movieId")?.let { movieId ->
            if (movieId.isNotEmpty()) {
                viewModelScope.launch {
                    useCases.movieDetails.invoke(Constants.LANG, movieId).collect {
                        when (it) {

                            is NetworkResult.Success -> {
                                it.value.body()?.let { response ->
                                    _movieDetailsResponse.value = response
                                    delay(1000)
                                    _isLoading[0] = false
                                }

                            }

                            is NetworkResult.Failure -> {
                                _apiError.value = true
                                _isLoading[0] = false
                            }

                            is NetworkResult.Loading -> {

                            }
                        }

                    }

                }

            }
        }
    }

}