package me.niklas.tools.frame.table

/**
 * @author Niklas Nieberler
 */

data class TableColumn<E>(
    val tableHeader: String,
    val value: (E) -> Any,
    val width: Int?,
)