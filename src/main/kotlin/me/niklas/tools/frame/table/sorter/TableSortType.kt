package me.niklas.tools.frame.table.sorter

import me.niklas.tools.frame.utils.DateFormatter

/**
 * @author Niklas Nieberler
 */

enum class TableSortType(
    private val comparator: TableSortComparator<out Any>
) : ITableSort {

    NUMBER(TableSortComparator<String> {
        Comparator.comparingInt { it.replace(".", "").toIntOrNull() ?: 0 }
    }),

    DATE(TableSortComparator<String> {
        Comparator.comparingLong { DateFormatter.stringToMillis(it) }
    }),

    MONTH(TableSortComparator<String> {
        Comparator.comparingLong { DateFormatter.stringToMillis(it, "01.$it") }
    });

    override fun getComparator(): TableSortComparator<out Any> = this.comparator

    // TODO: EOL irgendwie machen

    // TODO: sorter für so lieferanten (interface mit reinballern können)

}