package com.kazak.kirill.shoperset.data.credentials

import com.kazak.kirill.shoperset.data.credentials.storage.UserCredentialsStorage
import com.kazak.kirill.shoperset.data.credentials.storage.entity.UserCredentialsEntity
import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository
import com.kazak.kirill.shoperset.domain.credentials.model.UserCredentialsModel

class UserCredentialsRepositoryImpl(
    private val userCredentialsStorage: UserCredentialsStorage
): UserCredentialsRepository {

    private val mapper = UserCredentialsMapper()
    private var currentUserId = 0

    override suspend fun saveUserPhoto(userPhoto: String) {
        val currentUserData = userCredentialsStorage.getUserCredentialsById(currentUserId)
        userCredentialsStorage.saveUserCredentials(
            UserCredentialsEntity(
                userId = currentUserData.userId,
                userFirstName = currentUserData.userFirstName,
                userLastName = currentUserData.userLastName,
                userEmail = currentUserData.userEmail,
                userPhoto = userPhoto
            )
        )
    }

    override suspend fun saveUserCredentials(
        userFirstName: String,
        userLastName: String,
        userEmail: String
    ) {
        val userId = generateCredentialsId()
        userCredentialsStorage.saveUserCredentials(
            UserCredentialsEntity(
                userId = generateCredentialsId(),
                userFirstName = userFirstName,
                userLastName = userLastName,
                userEmail = userEmail,
                userPhoto = ""
            )
        )

        currentUserId = userId
    }


    override suspend fun deleteUserCredentials() {
        userCredentialsStorage.deleteUserCredentials()
    }

    override suspend fun getUserCredentialsList(): List<UserCredentialsModel> =
        mapper.mapUserCredentialsEntityListToModelList(
            userCredentialsStorage.getUserCredentialsList()
        )

    override suspend fun getUserName(): String {
        val userCred = userCredentialsStorage.getUserCredentialsById(currentUserId)
        return userCred.userFirstName + " " + userCred.userLastName
    }

    override suspend fun getUserPhoto() =
        userCredentialsStorage.getUserCredentialsById(currentUserId).userPhoto

    override suspend fun compareUserNames(nameFromLogin: String): Boolean {
        val userCredentialsList = userCredentialsStorage.getUserCredentialsList()
        var isSuccess = false

        for (i in userCredentialsList) {
            if (i.userFirstName == nameFromLogin) {
                currentUserId = i.userId
                isSuccess = true
                break
            } else {
                isSuccess = false
            }
        }

        return isSuccess
    }

    private suspend fun generateCredentialsId() : Int {
        val firstUserCredentialsId = 1

        return if (userCredentialsStorage.getUserCredentialsList().isEmpty()) {
            firstUserCredentialsId
        } else {
            userCredentialsStorage.getUserCredentialsList().size + firstUserCredentialsId
        }
    }
}