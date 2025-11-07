package com.example.medicinereminder

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("user_preferences")

class UserPreferences(private val context: Context) {
    companion object {
        val TEXT_SIZE = floatPreferencesKey("text_size")
    }

    val textSizeFlow: Flow<Float> = context.dataStore.data
        .map { preferences ->
            preferences[TEXT_SIZE] ?: 18f // valor por defecto
        }

    suspend fun saveTextSize(size: Float) {
        context.dataStore.edit { preferences ->
            preferences[TEXT_SIZE] = size
        }
    }
}
