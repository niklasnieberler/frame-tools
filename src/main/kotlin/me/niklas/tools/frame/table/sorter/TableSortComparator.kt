package me.niklas.tools.frame.table.sorter

/**
 * @author Niklas Nieberler
 */

fun interface TableSortComparator<T> {

    fun execute(): Comparator<T>

}