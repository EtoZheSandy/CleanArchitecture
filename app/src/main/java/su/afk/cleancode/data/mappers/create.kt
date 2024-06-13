package su.afk.cleancode.data.mappers

import su.afk.cleancode.data.models.AuthResponse
import su.afk.cleancode.domain.repository.models.User


fun AuthResponse.toUser(): User {
    return User(
        username = username,
        imageUrl = image)
}