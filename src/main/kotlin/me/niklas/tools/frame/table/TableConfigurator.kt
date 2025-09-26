package me.niklas.tools.frame.table

import javax.swing.table.DefaultTableModel

/**
 * @author Niklas Nieberler
 */

abstract class TableConfigurator<E> : DefaultTableModel() {

    val findRows = mutableListOf<E>()

    private val columns = mutableListOf<TableColumn<E>>()

    fun rows(list: List<E>) {
        this.findRows.addAll(list)
    }

    fun column(header: String, block: (E) -> Any) {
        column(header, null, block)
    }

    fun column(header: String, width: Int?, block: (E) -> Any) {
        this.columns.add(TableColumn(header, block, width))
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

    fun buildColumn(entity: E): Array<out Any> {
        return this.columns
            .map { it.value(entity) }.toTypedArray()
    }

}