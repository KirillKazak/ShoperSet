package com.kazak.kirill.shoperset.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kazak.kirill.shoperset.data.db.USER_CREDENTIALS_TABLE_NAME
import com.kazak.kirill.shoperset.data.db.entity.UserCredentialsEntity

@Dao
interface UserCredentialsDao {

    @Query("SELECT * FROM $USER_CREDENTIALS_TABLE_NAME ORDER BY userId DESC")
    suspend fun getUserCredentialsList(): List<UserCredentialsEntity>

    @Query("SELECT * FROM $USER_CREDENTIALS_TABLE_NAME WHERE userId=:userId ")
    suspend fun getUserCredentialsById(userId: Int): UserCredentialsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserCredentials(userCredentials: UserCredentialsEntity)

    @Query("DELETE FROM $USER_CREDENTIALS_TABLE_NAME")
    suspend fun deleteUserCredentials()
}