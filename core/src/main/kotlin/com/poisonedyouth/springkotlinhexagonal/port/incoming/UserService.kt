package com.poisonedyouth.springkotlinhexagonal.port.incoming

import com.poisonedyouth.springkotlinhexagonal.model.User
import java.util.Optional

interface UserService {
    fun getAllUser(): List<User>
    fun createUser(user: User): User
    fun getUserById(id: Long): Optional<User>
    fun updateUser(id: Long, user: User): Optional<User>
    fun deleteUser(id: Long)
}
