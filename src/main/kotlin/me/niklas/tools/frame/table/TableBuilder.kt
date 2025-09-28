package me.niklas.tools.frame.table

import me.niklas.tools.frame.table.renderer.TableColumnRenderer
import java.awt.event.KeyListener
import java.awt.event.MouseListener
import javax.swing.RowSorter.SortKey
import javax.swing.JTable

/**
 * @author Niklas Nieberler
 */

class TableBuilder(
    private val configurator: TableConfigurator<out Any>,
    val table: JTable,
    val container: TableContainer<out Any>
) {

    internal var mouseListener: MouseListener? = null
    internal var keyListener: KeyListener? = null

    fun <R : TableColumnRenderer> columnRenderer(
        renderer: R,
        fromColumn: Int = 0,
        toColumn: Int = this.configurator.getHeaders().size,
    ) {
        for (i in fromColumn until toColumn) {
            val column = this.table.columnModel.getColumn(i)
            column.cellRenderer = renderer
        }
    }

    fun sortKeysForRows(vararg sortKeys: SortKey) {
        val tableRowSorter = this.container.tableRowSorter
        val array = listOf(*sortKeys, *tableRowSorter.sortKeys.toTypedArray())
        tableRowSorter.sortKeys = array
    }

}