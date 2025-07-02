package com.ordresot.binviewer.data.local.api

import com.ordresot.binviewer.data.local.dto.Preference


interface PreferenceClient {
    fun getData(dto: Preference): Any?
    fun saveData(dto: Preference)
}