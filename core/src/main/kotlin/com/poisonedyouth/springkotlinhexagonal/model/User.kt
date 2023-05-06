package com.poisonedyouth.springkotlinhexagonal.model

data class User(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int
)