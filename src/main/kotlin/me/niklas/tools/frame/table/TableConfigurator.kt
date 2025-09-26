package me.niklas.tools.frame.table

import javax.swing.table.DefaultTableModel

/**
 * @author Niklas Nieberler
 */

abstract class TableConfigurator<E> : DefaultTableModel() {

    val headers = mutableListOf<String>()
    val findRows = mutableListOf<E>()

    private val columns = mutableListOf<(E) -> Any>()

    fun rows(list: List<E>) {
        this.findRows.addAll(list)
    }

    fun column(header: String, block: (E) -> Any) {
        this.headers.add(header)
        this.columns.add(block)
    }

    fun buildColumn(entity: E): Array<out Any> {
        return this.columns.map { it(entity) }.toTypedArray()
    }

}