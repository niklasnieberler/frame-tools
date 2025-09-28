package me.niklas.tools.frame.test

import me.niklas.tools.frame.table.TableConfigurator
import me.niklas.tools.frame.table.sorter.TableSortType
import java.util.UUID
import javax.swing.SortOrder

/**
 * @author Niklas Nieberler
 */

class TestTableConfigurator : TableConfigurator<String>() {

    init {
        rows(listOf("1", "2", "3", "4"))

        column("Test") { "1" }
        column("Hallo", sortType = TableSortType.NUMBER, sortOrder = SortOrder.DESCENDING) { it }
        column("was", 200) { UUID.randomUUID() }
    }

}