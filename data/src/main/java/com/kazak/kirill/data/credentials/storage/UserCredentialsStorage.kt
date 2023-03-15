package com.kazak.kirill.data.credentials.storage

import com.kazak.kirill.data.db.entity.UserCredentialsEntity

interface UserCredentialsStorage {

    suspend fun getUserCredentialsList(): List<UserCredentialsEntity>
    suspend fun getUserCredentialsById(userId: Int): UserCredentialsEntity
    suspend fun saveUserCredentials(userCredentials: UserCredentialsEntity)
    suspend fun deleteUserCredentials()
}