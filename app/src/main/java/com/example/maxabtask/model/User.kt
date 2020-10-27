package com.example.maxabtask.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("picture") var picture: Picture,
    @SerializedName("gender") var gender: String,
    @SerializedName("name") var name: Name,
    @SerializedName("login") var id: Id
)

data class Picture(@SerializedName("large") var image: String)

data class Name(
    @SerializedName("first") var firstName: String,
    @SerializedName("last") var lastName: String
)

data class Id(@SerializedName("uuid") var uuid: String)