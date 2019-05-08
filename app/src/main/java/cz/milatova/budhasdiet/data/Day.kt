package cz.milatova.budhasdiet.data

import java.time.LocalDateTime

class Day (
    val date: LocalDateTime,
    val stepId: Int,
    val eatingHours: Int,
    val daySuccesfull: Boolean // false = cheatday
) {
}