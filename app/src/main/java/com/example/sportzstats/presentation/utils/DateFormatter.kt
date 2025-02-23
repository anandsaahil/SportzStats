package com.example.sportzstats.presentation.utils

import java.text.SimpleDateFormat
import java.util.Locale
object DateFormatter {
    fun formatDateTime(dateString: String?, timeString: String?): String {
        return try {
            val inputFormatter = SimpleDateFormat("M/d/yyyy HH:mm", Locale.getDefault())
            val date = inputFormatter.parse("$dateString $timeString") ?: return "2 August, 2021 at 03:00 PM"

            val dateFormatter = SimpleDateFormat("d MMMM, yyyy", Locale.getDefault())
            val timeFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())

            val formattedDate = dateFormatter.format(date)
            val formattedTime = timeFormatter.format(date)

            "$formattedDate at $formattedTime"
        } catch (e: Exception) {
            e.printStackTrace()
            "2 August, 2021 at 03:00 PM"
        }
    }
}