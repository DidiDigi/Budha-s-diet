package cz.milatova.budhasdiet.data

import java.time.LocalDateTime

class Step (
    val stepId: Int,
    val eatingHours: Int,
    val nubmerOfSucceesfullDays: Int,
    val numberOfCheatDays: Int,
    val lastCheatDayDate: LocalDateTime,
    val nextStepId: Int,
    val canGoToNextStep: Boolean) {

}