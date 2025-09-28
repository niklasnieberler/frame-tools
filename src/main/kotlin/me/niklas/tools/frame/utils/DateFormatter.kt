package me.niklas.tools.frame.utils

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

/**
 * @author Niklas Nieberler
 */

object DateFormatter {

    fun stringToMillis(dateString: String, text: String = dateString): Long {
        val emptyDateInMillis = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(365 * 2000)
        if (dateString.isBlank() || dateString.contains("Unbekannt"))
            return emptyDateInMillis
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val localDate = LocalDate.parse(text, formatter)
        val instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        return instant.toEpochMilli()
    }

}