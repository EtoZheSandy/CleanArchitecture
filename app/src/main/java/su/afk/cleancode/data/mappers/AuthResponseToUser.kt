package su.afk.cleancode.data.mappers

import su.afk.cleancode.data.network.service.dto.AuthResponseDto
import su.afk.cleancode.domain.model.User


fun AuthResponseDto.toUser(): User {
    return User(
        firstName = firstName,
        lastName = lastName,
        email = email,
        imageUrl = image)
}