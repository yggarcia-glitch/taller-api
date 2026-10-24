package ec.edu.taller

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TallerApiApplication

fun main(args: Array<String>) {
    runApplication<TallerApiApplication>(*args)
}
