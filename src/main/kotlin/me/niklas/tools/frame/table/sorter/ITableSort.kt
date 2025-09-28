package me.niklas.tools.frame.table.sorter

/**
 * @author Niklas Nieberler
 */

interface ITableSort {

    fun getComparator(): TableSortComparator<out Any>

}