package com.sonusourav.atlys.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sonusourav.atlys.data.model.MovieDetailResponse
import com.sonusourav.atlys.domain.usecases.UseCases
import com.sonusourav.atlys.presentation.NavigationConstants.MOVIE_ID
import com.sonusourav.atlys.utils.Constants
import com.sonusourav.atlys.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetailsResponse = mutableStateOf(MovieDetailResponse())
    val movieDetailsResponse: State<MovieDetailResponse> = _movieDetailsResponse

    private val _apiError = mutableStateOf(false)
    val apiError: State<Boolean> = _apiError

    private var _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    init {
        savedStateHandle.get<String>(MOVIE_ID).takeIf { it.isNullOrBlank().not() }?.let { movieId ->
            viewModelScope.launch {
                useCases.movieDetails.invoke(Constants.LANG, movieId).collect {
                    when (it) {

                        is NetworkResult.Success -> {
                            it.value.body()?.let { response ->
                                _movieDetailsResponse.value = response
                                _isLoading.value = false
                            }
                        }

                        is NetworkResult.Failure -> {
                            _apiError.value = true
                            _isLoading.value = false
                        }

                        is NetworkResult.Loading -> {

                        }
                    }

                }

            }
        }
    }

}