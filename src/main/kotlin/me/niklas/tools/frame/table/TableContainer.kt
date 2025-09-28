package me.niklas.tools.frame.table

import javax.swing.JTable
import javax.swing.RowSorter
import javax.swing.table.TableRowSorter

/**
 * @author Niklas Nieberler
 */

class TableContainer<E : Any>(
    id: String,
    private val configurator: TableConfigurator<E>,
    val builder: TableBuilder.() -> Unit,
) {

    private val table = JTable(this.configurator)
    private val tableBuilder = TableBuilder(configurator, table, this)

    val tableRowSorter = TableRowSorter(this.configurator)

    init {
        table.tableHeader.reorderingAllowed = false
        // TODO: container irgendwo cachen zum updaten
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

        configureTableRowSorter()
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

    private fun configureTableRowSorter() {
        val columns = this.configurator.columns
        val columnComparators = columns.filter { it.sortType != null }
        columnComparators.forEach {
            val columnIndex = this.configurator.getColumnIndexByHeader(it.tableHeader)
                ?: throw NullPointerException("failed to find column index for header ${it.tableHeader}")
            val comparator = it.sortType!!.getComparator().execute()
            this.tableRowSorter.setComparator(columnIndex, comparator)
        }

        val sortKeys = columns
            .filter { it.sortOrder != null }
            .map {
                val columnIndex = this.configurator.getColumnIndexByHeader(it.tableHeader)
                    ?: throw NullPointerException("failed to find column index for header ${it.tableHeader}")
                RowSorter.SortKey(columnIndex, it.sortOrder!!)
            }

        val array = listOf(*sortKeys.toTypedArray(), *this.tableRowSorter.sortKeys.toTypedArray())
        this.tableRowSorter.sortKeys = array

        if (sortKeys.isNotEmpty() && columnComparators.isNotEmpty()) {
            this.table.rowSorter = this.tableRowSorter
        }
    }

}