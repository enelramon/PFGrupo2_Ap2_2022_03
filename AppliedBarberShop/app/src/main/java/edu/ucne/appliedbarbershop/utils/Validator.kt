package edu.ucne.appliedbarbershop.utils

sealed class Validator {

    fun isNumber(aux: String): Boolean {
        return try {
            aux.toDouble()
            false
        } catch (e: java.lang.NumberFormatException) {
            true
        }
    }

    // Aveces es necesario especificar que debe ser Int
    fun isInt(aux: String): Boolean {
        return try {
            aux.toInt()
            false
        } catch (e: java.lang.NumberFormatException) {
            true
        }
    }
}

fun validString(s: String?): String? {
    return s?.replace("\\s".toRegex(), "")
}