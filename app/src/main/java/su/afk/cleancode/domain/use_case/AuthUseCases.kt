package su.afk.cleancode.domain.use_case

import javax.inject.Inject

// собрал все use case в один класс для более простого внедрения в viewModel
data class AuthUseCases @Inject constructor(
    val loginUseCase: LoginUseCase,
    val authMeUseCase: AuthMeUseCase
)