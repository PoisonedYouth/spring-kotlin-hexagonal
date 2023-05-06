package com.poisonedyouth.springkotlinhexagonal.service

import com.poisonedyouth.springkotlinhexagonal.model.User
import com.poisonedyouth.springkotlinhexagonal.port.incoming.UserService
import com.poisonedyouth.springkotlinhexagonal.port.outgoing.UserRepository
import java.util.Optional

class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun getAllUser(): List<User> {
        return userRepository.findAll()
    }

    override fun getUserById(id: Long): Optional<User> {
        return userRepository.findById(id)
    }

    override fun createUser(user: User): User {
        return userRepository.save(user)
    }

    override fun updateUser(id: Long, user: User): Optional<User> {
        val userLoaded = userRepository.findById(id)
        if (userLoaded.isPresent) {
            val userUpdated = userLoaded.get().copy(
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email,
                age = user.age
            )
            return Optional.of(userRepository.save(userUpdated))
        }
        return Optional.empty()
    }

    override fun deleteUser(id: Long) {
        val user = userRepository.findById(id)
        if (user.isPresent) {
            userRepository.delete(user.get())
        }
    }
}