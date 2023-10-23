package com.globa.prodmir.data.login.internal.token

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.globa.prodmir.common.di.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TokenLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
){
    private val Context.dataStore by preferencesDataStore(name = "token_datastore")
    private val TOKEN_KEY = stringPreferencesKey("access_token")
    suspend fun getAccessToken() = withContext(dispatcher) {
        context.dataStore.data.first()[TOKEN_KEY]?: ""
    }

    suspend fun saveAccessToken(token: String) {
        withContext(dispatcher) {
            context.dataStore.edit {
                it[TOKEN_KEY] = token
            }
        }
    }
}