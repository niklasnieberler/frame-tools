package me.niklas.tools.frame.table

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import javax.swing.AbstractAction
import javax.swing.JTable
import javax.swing.KeyStroke

/**
 * @author Niklas Nieberler
 */

class TableContainer<E : Any>(
    id: String,
    private val configurator: TableConfigurator<E>,
    val builder: TableBuilder.() -> Unit
) {

    private val table = JTable(this.configurator)
    private val tableBuilder = TableBuilder(configurator, table)

    init {
        table.tableHeader.reorderingAllowed = false
    }

    fun createTable(): JTable {
        this.table.addMouseListener(this.tableBuilder.mouseListener)
        this.table.addKeyListener(this.tableBuilder.keyListener)

        val dataRows = this.configurator.findRows
            .map { this.configurator.buildColumn(it) }
            .toTypedArray()
        this.configurator.setDataVector(dataRows, this.configurator.getHeaders())

        changeColumnWidth()
        TableColumnCopyStroke(this.table, this.configurator).setStrokeIntoTable()

        this.tableBuilder.builder()
        return this.table
    }

    private fun changeColumnWidth() {
        val columnWidths = this.configurator.getColumnWidths()
        columnWidths
            .filter { it.value != null }
            .forEach { entry ->
                val column = this.table.columnModel.getColumn(entry.key)
                entry.value?.let { column.preferredWidth = it }
            }
    }

}