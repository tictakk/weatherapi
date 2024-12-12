package com.example.nooruapp.components

//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationSearchBar(
    modifier: Modifier,
    activeChange: (Boolean) -> Unit,
    location: String? = "",
    updateLocation: (String) -> Unit,
    search: (String) -> Unit,
    isSearching: Boolean
){
    Column(modifier = modifier.fillMaxWidth()){
        SearchBar(
            modifier = modifier.align(Alignment.CenterHorizontally),
            query = location!!,
            onQueryChange = updateLocation,
            onSearch = search,
            active = isSearching,
            onActiveChange = {status -> activeChange(status)},
            trailingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFFC4C4C4)) },
            placeholder = {Text("Search Location", color = Color(0xFFC4C4C4))}
        ){
            //some content here?
        }
    }
}