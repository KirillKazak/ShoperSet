package com.kazak.kirill.shoperset.data.credentials.storage

import android.content.Context
import androidx.room.Room
import com.kazak.kirill.shoperset.data.credentials.storage.entity.UserCredentialsEntity
import com.kazak.kirill.shoperset.util.Constants.USER_CREDENTIALS_DATABASE_NAME

class UserCredentialsStorageImpl(context: Context): UserCredentialsStorage {

    private val userCredentialsDatabase by lazy {
        Room.databaseBuilder(
            context,
            UserCredentialsDatabase::class.java,
            USER_CREDENTIALS_DATABASE_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun getUserCredentialsList(): List<UserCredentialsEntity> =
        userCredentialsDatabase.userCredentialsItemDao().getUserCredentialsList()

    override fun saveUserCredentials(userCredentials: UserCredentialsEntity) {
        userCredentialsDatabase.userCredentialsItemDao()
            .saveUserCredentials(userCredentials = userCredentials)
    }

    override fun deleteUserCredentials() {
        userCredentialsDatabase.userCredentialsItemDao().deleteUserCredentials()
    }
}