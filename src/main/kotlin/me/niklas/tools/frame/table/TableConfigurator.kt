package me.niklas.tools.frame.table

import javax.swing.table.DefaultTableModel

/**
 * @author Niklas Nieberler
 */

abstract class TableConfigurator<E> : DefaultTableModel() {

    val findRows = mutableListOf<E>()

    val columns = mutableListOf<TableColumn<E>>()

    fun rows(list: List<E>) {
        this.findRows.addAll(list)
    }

    fun column(header: String, block: (E) -> Any) {
        column(header, null, block)
    }

    fun column(header: String, width: Int?, block: (E) -> Any) {
        this.columns.add(TableColumn(header, block, width))
    }

    fun column(header: String, width: Int? = null, copyColumn: Boolean = false, block: (E) -> Any) {
        this.columns.add(TableColumn(header, block, width, copyColumn))
    }

    fun getHeaders(): Array<String> {
        return this.columns.map { it.tableHeader }
            .toTypedArray()
    }

    fun getColumnWidths(): Map<Int, Int?> {
        return this.columns
            .mapIndexed { index, column -> index to column.width }
            .toMap()
    }

    fun getColumnIndexByHeader(header: String): Int? {
        val tableColumn = this.columns.firstOrNull { it.tableHeader == header } ?: return null
        return this.columns.indexOf(tableColumn)
    }

    fun indexOfColumn(tableColumn: TableColumn<E>): Int {
        return this.columns.indexOf(tableColumn)
    }

    fun buildColumn(entity: E): Array<out Any> {
        return this.columns
            .map { it.value(entity) }.toTypedArray()
    }

    override fun isCellEditable(row: Int, column: Int): Boolean {
        return column == 0
    }

}