package com.example.maxabtask.util

import com.example.maxabtask.datamodel.local.entities.UserEntity
import com.example.maxabtask.model.Id
import com.example.maxabtask.model.Name
import com.example.maxabtask.model.Picture
import com.example.maxabtask.model.User

fun List<User>.toUserEntities(): ArrayList<UserEntity> {
    val entities = ArrayList<UserEntity>()
    for (user in this) {
        entities.add(
            UserEntity(
                user.id.uuid,
                user.name.firstName,
                user.name.lastName,
                user.gender,
                user.picture.image
            )
        )
    }
    return entities
}

fun List<UserEntity>.toUserModels(): ArrayList<User> {
    val models = ArrayList<User>()
    for (entity in this) {
        models.add(
            User(
                Picture(entity.picture),
                entity.gender,
                Name(entity.firstName, entity.lastName),
                Id(entity.id)
            )
        )
    }
    return models
}