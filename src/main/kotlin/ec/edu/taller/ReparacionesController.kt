package ec.edu.taller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/reparaciones")
class ReparacionesController {

    // Lista en memoria: se pierde al reiniciar
    private val reparaciones = mutableListOf(
        Reparacion(1, "Bicicleta de montaña", Cliente("Juan Pérez", "0991112233"),
            listOf("llanta", "cámara"), 25.0, true),
        Reparacion(2, "Bicicleta de ruta", Cliente("María López", "0984445566"),
            listOf("cadena"), 15.5, false),
    )
    private var siguienteId = 3

    // GET /reparaciones -> lista completa
    @GetMapping
    fun listar(): List<Reparacion> = reparaciones

    // GET /reparaciones/{id} -> una, o 404 si no existe
    @GetMapping("/{id}")
    fun obtener(@PathVariable id: Int): ResponseEntity<Reparacion> =
        reparaciones.find { it.id == id }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    // POST /reparaciones -> crea, asigna id, lista=false, responde 201 + Location
    @PostMapping
    fun crear(@RequestBody nueva: NuevaReparacion): ResponseEntity<Reparacion> {
        val reparacion = Reparacion(
            id = siguienteId++,
            bicicleta = nueva.bicicleta,
            cliente = nueva.cliente,
            repuestos = nueva.repuestos,
            costo = nueva.costo,
            lista = false,
        )
        reparaciones.add(reparacion)
        return ResponseEntity.created(URI("/reparaciones/${reparacion.id}")).body(reparacion)
    }
}
