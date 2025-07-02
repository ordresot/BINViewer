package com.ordresot.binviewer.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.ordresot.binviewer.data.local.api.PreferenceClient
import com.ordresot.binviewer.data.local.dto.Preference
import javax.inject.Inject

class SharedPrefsClient @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
): PreferenceClient {
    override fun getData(dto: Preference): Any? {
        return gson.fromJson(
            sharedPreferences.getString(dto.key, null),
            dto.type
        )
    }

    override fun saveData(dto: Preference) {
        sharedPreferences.edit {
            putString(
                dto.key,
                gson.toJson(dto.value)
            )
        }
    }
}