package me.niklas.tools.frame.table

import me.niklas.tools.frame.table.sorter.ITableSort
import javax.swing.SortOrder

/**
 * @author Niklas Nieberler
 */

data class TableColumn<E>(
    val tableHeader: String,
    val value: (E) -> Any,
    val width: Int?,
    val editable: Boolean = false,
    val copyColumn: Boolean = false,
    val sortType: ITableSort? = null,
    val sortOrder: SortOrder? = null
)