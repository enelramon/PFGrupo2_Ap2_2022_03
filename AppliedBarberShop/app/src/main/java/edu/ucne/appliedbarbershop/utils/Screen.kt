package edu.ucne.appliedbarbershop.utils

sealed class Screen(val Route: String, val MostrarNav: Boolean = true) {
    object IntroScreen: Screen("IntroScreen", false)

    object PrincipalScreen: Screen("PrincipalScreen")

    object ConsultaMisPerfilesScreen: Screen("ConsultaMisPerfilesScreen")
    object RegistroPerfilScreen: Screen("RegistroPerfilScreen", false)
    object ConfirmaRegistroPerfilScreen: Screen("ConfirmaRegistroPerfilScreen", false)

    object ConsultaMisCitasScreen: Screen("ConsultaMisCitasScreen")
    object RegistroCita_BarberoScreen: Screen("RegistroCita_BarberoScreen")
    object RegistroCita_ServicioScreen: Screen("RegistroCita_ServicioScreen")
    object RegistroCita_ReservacionScreen: Screen("RegistroCita_ReservacionScreen")
    object ConfirmaRegistroCitaScreen: Screen("ConfirmaRegistroCitaScreen", false)

    object ConsultaCitasPendientesScreen: Screen("ConsultaCitasPendientesScreen")
    object ConsultaHistorialCitasScreen: Screen("ConsultaHistorialCitasScreen")

    object ConsultaServiciosScreen: Screen("ConsultaServiciosScreen")
    object RegistroServicioScreen: Screen("RegistroServicioScreen")

    object AjustesScreen: Screen("AjustesScreen")
}