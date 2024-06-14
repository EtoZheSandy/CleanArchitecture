package su.afk.cleancode.data.mappers

import su.afk.cleancode.data.network.service.models.AuthResponse
import su.afk.cleancode.domain.models.User


fun AuthResponse.toUser(): User {
    return User(
        firstName = firstName,
        lastName = lastName,
        email = email,
        imageUrl = image)
}