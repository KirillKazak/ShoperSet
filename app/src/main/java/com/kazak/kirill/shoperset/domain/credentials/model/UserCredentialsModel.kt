package com.kazak.kirill.shoperset.domain.credentials.model

data class UserCredentialsModel (
    val userId: Int,
    val userFirstName: String,
    val userLastName: String,
    val userEmail: String
        )