package viewModel

import data.remote.dto.Hour
import data.remote.dto.Weather

fun Weather.toUIState(): HomeUIState {
    return HomeUIState(
        forecastHourly = forecast.forecastday[0].hour?.map { it.toUIState() } ?: emptyList(),
        windDegree = (current?.windDegree ?: 0).toFloat(),
        windKph = current?.windKph ?: 0.0,
        humidityValue = "${current?.humidity}%",
        humidityDescription = "Normal",
        visibilityAvg = "${current?.visKm} Km",
        feelsLike = "${current?.feelslikeC}",
        feelDescription = current?.condition?.text ?: ""
    )
}

fun Hour.toUIState() = ForecastHour(
    time = convertEpochToTimeString(timeEpoch),
    icon = condition?.icon ?: "",
    temp = tempC ?: 0.0
)

fun convertEpochToTimeString(epoch: Long): String {
    val hours = (epoch / 3600) % 24 // Calculate hours
    val amPm = if (hours >= 12) "PM" else "AM" // Determine AM/PM

    val formattedHours = when (hours % 12) {
        0L -> 12
        else -> hours % 12
    }

    return String.format("%d %s", formattedHours, amPm)
}