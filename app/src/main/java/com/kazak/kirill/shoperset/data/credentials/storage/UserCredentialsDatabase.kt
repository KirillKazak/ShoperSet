package com.kazak.kirill.shoperset.data.credentials.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kazak.kirill.shoperset.data.credentials.storage.entity.UserCredentialsEntity

@Database(
    entities = [UserCredentialsEntity::class],
    version = 1,
    exportSchema = true
)
abstract class UserCredentialsDatabase: RoomDatabase() {

    abstract fun userCredentialsItemDao(): UserCredentialsDao
}