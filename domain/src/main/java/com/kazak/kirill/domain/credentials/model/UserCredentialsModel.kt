package com.kazak.kirill.domain.credentials.model

data class UserCredentialsModel (
    val userId: Int,
    val userFirstName: String,
    val userLastName: String,
    val userEmail: String,
    var userPhoto: String
        )