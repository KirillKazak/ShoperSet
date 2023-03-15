package com.kazak.kirill.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kazak.kirill.data.db.entity.UserCredentialsEntity
import com.kazak.kirill.data.db.dao.UserCredentialsDao

@Database(
    entities = [UserCredentialsEntity::class],
    version = 1,
    exportSchema = true
)
abstract class UserCredentialsDatabase: RoomDatabase() {

    abstract fun userCredentialsItemDao(): UserCredentialsDao
}