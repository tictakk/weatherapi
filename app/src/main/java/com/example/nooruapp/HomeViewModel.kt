package com.example.nooruapp

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.dataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nooruapp.data.ApiService
import com.example.nooruapp.data.RetrofitClient
import com.example.nooruapp.data.UserPreferences
import com.example.nooruapp.data.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

data class WeatherUiState(
    val search: String? = "",
    val city: String? = "",
    val humidity: Int = 0,
    val condition: String? = ""
)

sealed interface HomeUiState{
    data object Searched: HomeUiState
    data object Loaded: HomeUiState
    data object Startup: HomeUiState
}

@HiltViewModel
class HomeViewModel @Inject constructor(private val retrofit: RetrofitClient): ViewModel() {
    private val _weatherResponse: MutableLiveData<Response<WeatherData>> = MutableLiveData()
    val weatherResponse: LiveData<Response<WeatherData>> get() = _weatherResponse

    private val _searchLocationState = MutableStateFlow(String())
    val searchLocationState: StateFlow<String>
        get() = _searchLocationState

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState.Startup)
    val state: StateFlow<HomeUiState>
        get() = _state

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean>
        get() = _isSearching

    fun updateSearchField(location: String){
        _searchLocationState.update { location }
    }

    fun updateIsSearching(isSearching: Boolean){
        _isSearching.value = isSearching
    }

    fun searchForWeather(location: String) = viewModelScope.launch{
        updateIsSearching(false)
        _weatherResponse.value = retrofit.provideApiService().getWeatherData("015d3a4a4c3d40e79f5204348241012",location)
        _state.update { HomeUiState.Searched }
    }

    fun setHomeWeather(location: String) = viewModelScope.launch {
        _weatherResponse.value = retrofit.provideApiService().getWeatherData("015d3a4a4c3d40e79f5204348241012",location)
        _state.update { HomeUiState.Loaded }
    }

    fun searchCardClicked(context: Context) = viewModelScope.launch {
        _state.update { HomeUiState.Loaded }
        UserPreferences(context).saveLocation(weatherResponse.value?.body()?.location?.name!!)
        _searchLocationState.value = ""
    }
}