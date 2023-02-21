package com.example.data.local

import android.content.SharedPreferences
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.inject.Inject

class AuthPreferenceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : AuthPreference {

    override suspend fun saveAccessToken(accessToken: String) =
        saveStringPreference(ACCESS_TOKEN, accessToken)

    override suspend fun fetchAccessToken(): String =
        fetchStringPreference(ACCESS_TOKEN)

    override suspend fun clearAccessToken() =
        clearPreference(ACCESS_TOKEN)

    override suspend fun saveRefreshToken(refreshToken: String) =
        saveStringPreference(REFRESH_TOKEN, refreshToken)

    override suspend fun fetchRefreshToken(): String =
        fetchStringPreference(REFRESH_TOKEN)

    override suspend fun clearRefreshToken() =
        clearPreference(REFRESH_TOKEN)

    override suspend fun saveExpirationAt(expiredAt: ZonedDateTime) =
        saveLongPreference(EXPIRED_AT, expiredAt.toEpochSecond())

    override suspend fun fetchExpirationAt(): ZonedDateTime =
        Instant.ofEpochSecond(fetchLongPreference(EXPIRED_AT)).atZone(ZoneId.systemDefault())

    override suspend fun clearExpirationAt() =
        clearPreference(EXPIRED_AT)

    override suspend fun saveRole(role: String) =
        saveStringPreference(ROLE, role)

    override suspend fun fetchRole(): String =
        fetchStringPreference(ROLE)

    override suspend fun clearRole() =
        clearPreference(ROLE)

    private fun fetchStringPreference(key: String): String =
        sharedPreferences.getString(key, null) ?: ""

    private fun saveStringPreference(key: String, value: String) =
        editPreference { it.putString(key, value) }

    private fun fetchLongPreference(key: String): Long =
        sharedPreferences.getLong(key, 0)

    private fun saveLongPreference(key: String, value: Long) =
        editPreference { it.putLong(key, value) }

    private fun clearPreference(key: String) =
        editPreference { it.remove(key) }


    private fun editPreference(edit: (SharedPreferences.Editor) -> Unit) =
        sharedPreferences.edit().let {
            edit(it)
            it.apply()
        }

    companion object Key {
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
        const val EXPIRED_AT = "EXPIRED_AT"
        const val ROLE = "ROLE"
    }
}
