package su.afk.cleancode.data.mappers

import su.afk.cleancode.data.network.service.dto.AuthResponse
import su.afk.cleancode.domain.model.User


fun AuthResponse.toUser(): User {
    return User(
        firstName = firstName,
        lastName = lastName,
        email = email,
        imageUrl = image)
}