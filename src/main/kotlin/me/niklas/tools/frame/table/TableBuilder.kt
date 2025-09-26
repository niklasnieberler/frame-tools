package me.niklas.tools.frame.table

/**
 * @author Niklas Nieberler
 */

class TableBuilder {

    internal var _headers: List<String> = emptyList()
    internal var _rows: List<String> = emptyList()

    fun headers(vararg headers: String) {
        _headers = headers.toList()
    }

    fun rows(rows: List<String>) {
        _rows = rows
    }


}