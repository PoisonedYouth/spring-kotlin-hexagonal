package com.poisonedyouth.springkotlinhexagonal.port.outgoing

import com.poisonedyouth.springkotlinhexagonal.model.User
import java.util.Optional

interface UserRepository {
    fun findAll(): List<User>
    fun findById(id: Long): Optional<User>
    fun save(user: User): User
    fun delete(user: User)
}