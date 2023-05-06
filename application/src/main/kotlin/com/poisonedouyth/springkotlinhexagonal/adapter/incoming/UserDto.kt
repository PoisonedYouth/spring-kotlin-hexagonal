package com.poisonedouyth.springkotlinhexagonal.adapter.incoming

import com.poisonedyouth.springkotlinhexagonal.model.User

data class UserDto(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int
)

fun UserDto.toUser() = User(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    age = this.age
)