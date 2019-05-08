package cz.milatova.budhasdiet.data.model

import java.time.LocalDateTime

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
        val userId: String,
        val displayName: String,
        val name: String,
        val surname: String,
        val birthdayDate: LocalDateTime,
        val image: String
)
