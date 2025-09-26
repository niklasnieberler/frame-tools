package me.niklas.tools.frame.test

import me.niklas.tools.frame.table.TableConfigurator

/**
 * @author Niklas Nieberler
 */

class TestTableConfigurator : TableConfigurator<String>() {

    init {
        rows(listOf("1", "2", "3", "4"))

        column("Test") { "1" }
        column("Hallo") { it }
    }

}