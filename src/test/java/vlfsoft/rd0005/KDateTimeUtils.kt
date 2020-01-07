package vlfsoft.rd0005

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneId

val currentLocalDateTime: LocalDateTime get() = LocalDateTime.now(ZoneId.systemDefault())
inline val currentLocalDate: LocalDate get() = currentLocalDateTime.toLocalDate()

val Number.days: Period get() = Period.ofDays(toInt())

/**
 * https://stackoverflow.com/questions/33530011/java-easiest-way-to-subtract-dates
 * Subtracting Dates
 */
operator fun LocalDate.minus(minusValue: LocalDate) = Period.between(minusValue, this).days

operator fun LocalDate.inc(): LocalDate = this + 1.days
operator fun LocalDate.dec(): LocalDate = this - 1.days
