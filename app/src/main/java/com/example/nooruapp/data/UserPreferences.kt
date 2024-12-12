package com.example.nooruapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object PrefenceKeys{
    val SAVED_LOCATION_KEY = stringPreferencesKey("SAVED_LOCATION")
}

class UserPreferences(val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    }

    suspend fun getSavedLocation(): String? {
        val flow: Flow<String> = context.dataStore.data
            .map { preferences ->
                preferences[PrefenceKeys.SAVED_LOCATION_KEY] ?: "NONE"
            }
        return flow.first()
    }

    suspend fun saveLocation(location: String){
        context.dataStore.edit { preferences ->
            preferences[PrefenceKeys.SAVED_LOCATION_KEY] = location
        }
    }
}