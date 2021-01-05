package org.service.demo.util

import android.content.Context
import android.content.Context.MODE_PRIVATE

class LocationPrefs(private val context: Context) {

    companion object {
        private const val PREFS_NAME = "uk.nhs.covid.app"
        private const val POSTAL_KEY = "postal_code"
        private const val DESC_KEY = "description"
    }

    internal fun savePostalCode(value: String) {
        val postalCode = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit()
        postalCode.putString(POSTAL_KEY, value).apply()
    }

    internal fun saveDescription(value: String) {
        val description = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit()
        description.putString(DESC_KEY, value).apply()
    }

    internal fun getPostalCode(): String? =
        context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).getString(POSTAL_KEY, "")

    internal fun getDescription(): String? =
        context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).getString(DESC_KEY, "")
}
