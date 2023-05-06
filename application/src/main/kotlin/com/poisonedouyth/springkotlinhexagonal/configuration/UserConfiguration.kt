package com.poisonedouyth.springkotlinhexagonal.configuration

import com.poisonedyouth.springkotlinhexagonal.port.incoming.UserService
import com.poisonedyouth.springkotlinhexagonal.port.outgoing.UserRepository
import com.poisonedyouth.springkotlinhexagonal.service.UserServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserConfiguration {

    @Bean
    fun userService(userRepository: UserRepository): UserService = UserServiceImpl(userRepository)

}