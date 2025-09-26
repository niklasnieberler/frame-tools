package me.niklas.tools.frame.test

import me.niklas.tools.frame.table.TableConfigurator
import java.util.UUID

/**
 * @author Niklas Nieberler
 */

class TestTableConfigurator : TableConfigurator<String>() {

    init {
        rows(listOf("1", "2", "3", "4"))

        column("Test") { "1" }
        column("Hallo") { it }
        column("was", 200) { UUID.randomUUID() }
    }

}