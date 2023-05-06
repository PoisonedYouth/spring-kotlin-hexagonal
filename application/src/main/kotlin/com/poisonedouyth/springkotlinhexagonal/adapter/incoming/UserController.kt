package com.poisonedouyth.springkotlinhexagonal.adapter.incoming

import com.poisonedyouth.springkotlinhexagonal.model.User
import com.poisonedyouth.springkotlinhexagonal.port.incoming.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.optionals.getOrNull

@RestController
@RequestMapping("api/v1")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/users")
    fun getUsers(): List<UserDto> {
        return userService.getAllUser().map { it.toDto() }
    }

    @OptIn(ExperimentalStdlibApi::class)
    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: Long): UserDto? {
        return userService.getUserById(id).getOrNull()?.let { it.toDto() }
    }

    @PostMapping("/users")
    fun createUser(@RequestBody userDto: UserDto): UserDto {
        return userService.createUser(userDto.toUser()).toDto()
    }

    @OptIn(ExperimentalStdlibApi::class)
    @PutMapping("/users/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody userDto: UserDto): UserDto? {
        return userService.updateUser(id, userDto.toUser()).getOrNull()?.toDto()
    }

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteUser(id)
    }

    private fun User.toDto() = UserDto(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        age = this.age
    )
}