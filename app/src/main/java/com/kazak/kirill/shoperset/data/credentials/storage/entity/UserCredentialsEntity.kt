package com.kazak.kirill.shoperset.data.credentials.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kazak.kirill.shoperset.util.Constants.USER_CREDENTIALS_TABLE_NAME

@Entity(tableName = USER_CREDENTIALS_TABLE_NAME)
data class UserCredentialsEntity(
    @PrimaryKey()
    @ColumnInfo(name = "userId")
    val userId: Int,

    @ColumnInfo(name = "userFirstName")
    val userFirstName: String,

    @ColumnInfo(name = "userLastName")
    val userLastName: String,

    @ColumnInfo(name = "userEmail")
    val userEmail: String
)
