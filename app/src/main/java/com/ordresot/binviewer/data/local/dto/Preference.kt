package com.ordresot.binviewer.data.local.dto

import java.lang.reflect.Type

open class Preference(
    var value: Any? = null,
    val key: String? = null,
    val type: Type? = null
)