package co.selim.ext

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val timestampFormatter = DateTimeFormatter.ofPattern("uuuuMMdd-HHmmss")
private val dateFormatter = DateTimeFormatter.ofPattern("uuuuMMdd")

fun timestampString(): String = timestampFormatter.format(LocalDateTime.now())
fun dateString(): String = dateFormatter.format(LocalDate.now())