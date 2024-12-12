package com.example.nooruapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nooruapp.components.ConditionsBar
import com.example.nooruapp.components.LocationSearchBar
import com.example.nooruapp.components.SearchedCard
import com.example.nooruapp.components.WeatherCard
import com.example.nooruapp.data.Current
import com.example.nooruapp.data.UserPreferences
import kotlinx.coroutines.runBlocking


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier
){
    val context = LocalContext.current
    val uiState by viewModel.searchLocationState.collectAsState()
    val weather = viewModel.weatherResponse.observeAsState()
    val homeScreenState by viewModel.state.collectAsStateWithLifecycle()
    val isSearching by viewModel.isSearching.collectAsState()

    Column(
        verticalArrangement = Arrangement.Top
    ) {
        //when clicking search result, save location as prefered city
        LocationSearchBar(
            modifier = modifier,
            activeChange = {viewModel.updateIsSearching(it)},
            location = uiState,
            search = {viewModel.searchForWeather(uiState!!)},
            updateLocation = {viewModel.updateSearchField(it)},
            isSearching = isSearching
        )
        when(val uiState = homeScreenState){
            is HomeUiState.Loaded -> { WeatherCard(modifier = modifier, data = weather.value?.body())}
            is HomeUiState.Searched -> { SearchedCard(
                modifier = modifier,
                onClicked = {viewModel.searchCardClicked(context) },
                weather.value?.body())
            }
            is HomeUiState.Startup -> {
                val location = runBlocking { UserPreferences(context).getSavedLocation() }
                viewModel.setHomeWeather(location!!)
                WeatherCard(modifier = modifier, data = weather.value?.body())
            }
        }
    }
}