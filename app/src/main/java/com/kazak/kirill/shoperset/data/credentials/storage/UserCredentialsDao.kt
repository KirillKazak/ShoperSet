package com.kazak.kirill.shoperset.data.credentials.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kazak.kirill.shoperset.data.credentials.storage.entity.UserCredentialsEntity
import com.kazak.kirill.shoperset.util.Constants.USER_CREDENTIALS_TABLE_NAME

@Dao
interface UserCredentialsDao {

    @Query("SELECT * FROM $USER_CREDENTIALS_TABLE_NAME ORDER BY userId DESC")
    fun getUserCredentialsList(): List<UserCredentialsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserCredentials(userCredentials: UserCredentialsEntity)

    @Query("DELETE FROM $USER_CREDENTIALS_TABLE_NAME")
    fun deleteUserCredentials()
}