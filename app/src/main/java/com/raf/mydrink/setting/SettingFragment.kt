package com.raf.mydrink.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.raf.mydrink.R

class SettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        val listPrefTheme = findPreference<ListPreference>(getString(R.string.pref_key_dark))
        listPrefTheme?.setOnPreferenceChangeListener { _, newValue ->
            when(newValue) {
                getString(R.string.pref_dark_on) -> updateTheme(NightMode.ON.value)
                getString(R.string.pref_dark_off) -> updateTheme(NightMode.OFF.value)
                else -> updateTheme(NightMode.AUTO.value)
            }
        }
    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }
}