package me.niklas.tools.frame.table

import javax.swing.JTable
import javax.swing.table.DefaultTableModel

/**
 * @author Niklas Nieberler
 */

class TableContainer<E : Any>(
    id: String,
    private val configurator: TableConfigurator<E>,
    builder: TableBuilder.() -> Unit
) {

    private val tableModel = DefaultTableModel()
    private val table = JTable(tableModel)
    private val tableBuilder = TableBuilder(table)

    init {
        tableBuilder.builder()
    }

    fun createTable(): JTable {
        this.table.addMouseListener(this.tableBuilder.mouseListener)
        this.table.addKeyListener(this.tableBuilder.keyListener)

        val dataRows = this.configurator.findRows
            .map { this.configurator.buildColumn(it) }
            .toTypedArray()
        this.tableModel.setDataVector(dataRows, this.configurator.getHeaders())

        changeColumnWidth()
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