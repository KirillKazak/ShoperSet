package com.kazak.kirill.shoperset.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kazak.kirill.shoperset.data.db.entity.UserCredentialsEntity
import com.kazak.kirill.shoperset.data.db.dao.UserCredentialsDao

@Database(
    entities = [UserCredentialsEntity::class],
    version = 1,
    exportSchema = true
)
abstract class UserCredentialsDatabase: RoomDatabase() {

    abstract fun userCredentialsItemDao(): UserCredentialsDao
}