package br.fema.edu.squidconf.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalTime

data class TimeRule(@JsonFormat(pattern = "hh:mm") val begin: LocalTime, @JsonFormat(pattern = "hh:mm") val end: LocalTime, val deny: Boolean, val nome: String)