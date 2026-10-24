package ec.edu.taller

// Datos del cliente de la reparación
data class Cliente(
    val nombre: String,
    val telefono: String,
)

// Lo que DEVUELVE el servidor (incluye id y lista, que asigna el servidor)
data class Reparacion(
    val id: Int,
    val bicicleta: String,
    val cliente: Cliente,
    val repuestos: List<String>,
    val costo: Double,
    val lista: Boolean,
)

// Lo que ENVÍA el cliente al crear (sin id ni lista: los pone el servidor)
data class NuevaReparacion(
    val bicicleta: String,
    val cliente: Cliente,
    val repuestos: List<String> = emptyList(),
    val costo: Double,
)
