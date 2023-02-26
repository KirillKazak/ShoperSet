package com.kazak.kirill.shoperset.data.credentials

import com.kazak.kirill.shoperset.data.credentials.storage.entity.UserCredentialsEntity
import com.kazak.kirill.shoperset.domain.credentials.model.UserCredentialsModel

class UserCredentialsMapper {

    fun mapUserCredentialsModelToEntity(
        userCredentialsModel: UserCredentialsModel
    ): UserCredentialsEntity =
        UserCredentialsEntity (
            userId = userCredentialsModel.userId,
            userFirstName = userCredentialsModel.userFirstName,
            userLastName = userCredentialsModel.userLastName,
            userEmail = userCredentialsModel.userEmail
        )

    private fun mapUserCredentialsEntityToModel(
        userCredentialsEntity: UserCredentialsEntity
    ) =
        UserCredentialsModel(
                    userId = userCredentialsEntity.userId,
                    userFirstName = userCredentialsEntity.userFirstName,
                    userLastName = userCredentialsEntity.userLastName,
                    userEmail = userCredentialsEntity.userEmail
                )


    fun mapUserCredentialsEntityListToModelList(
        userCredentialsEntityList: List<UserCredentialsEntity>
    ): List<UserCredentialsModel> =
        userCredentialsEntityList.map {
            mapUserCredentialsEntityToModel(it)
        }

}