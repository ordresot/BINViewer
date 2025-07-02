package com.ordresot.binviewer.data.local.dto

import com.google.gson.reflect.TypeToken

class BINHistoryPreference(): Preference(key = PreferenceKey.BIN_HISTORY.key, type = object : TypeToken<ArrayList<BINInfoDto>>() {}.type) {
    constructor(value: List<BINInfoDto>) : this() {
        this.value = value
    }
}