package com.kazak.kirill.data.credentials

import com.kazak.kirill.data.db.entity.UserCredentialsEntity
import com.kazak.kirill.domain.credentials.model.UserCredentialsModel

class UserCredentialsMapper {

    private fun mapUserCredentialsEntityToModel(
        userCredentialsEntity: UserCredentialsEntity
    ): UserCredentialsModel =
        UserCredentialsModel(
            userId = userCredentialsEntity.userId,
            userFirstName = userCredentialsEntity.userFirstName,
            userLastName = userCredentialsEntity.userLastName,
            userEmail = userCredentialsEntity.userEmail,
            userPhoto = userCredentialsEntity.userPhoto
                )


    fun mapUserCredentialsEntityListToModelList(
        userCredentialsEntityList: List<UserCredentialsEntity>
    ): List<UserCredentialsModel> =
        userCredentialsEntityList.map {
            mapUserCredentialsEntityToModel(it)
        }

}