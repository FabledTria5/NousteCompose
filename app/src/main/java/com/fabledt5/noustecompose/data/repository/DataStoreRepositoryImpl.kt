package com.fabledt5.noustecompose.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.fabledt5.noustecompose.domain.repository.DataStoreRepository
import com.fabledt5.noustecompose.domain.util.Constants.DATASTORE_NAME
import com.fabledt5.noustecompose.domain.util.Constants.LIST_ORDER_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

class DataStoreRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DataStoreRepository {

    private object PreferencesKeys {
        val listOrderKey = stringPreferencesKey(name = LIST_ORDER_KEY)
    }

    private val dataStore = context.dataStore

    override suspend fun persistListOrders(listOrder: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.listOrderKey] = listOrder
        }
    }

    override val listOrder: Flow<String> = dataStore.data
        .catch { exception ->
            Timber.e(exception)
            emit(emptyPreferences())
        }
        .map { preferences ->
            preferences[PreferencesKeys.listOrderKey] ?: ""
        }

}