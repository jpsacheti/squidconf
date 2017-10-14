package br.fema.edu.squidconf.model

import java.time.LocalTime

data class TimeRule(val begin: LocalTime, val end: LocalTime, val deny: Boolean, val nome: String)