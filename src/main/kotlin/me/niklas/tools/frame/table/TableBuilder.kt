package me.niklas.tools.frame.table

import java.awt.event.KeyListener
import java.awt.event.MouseListener
import javax.swing.JTable
import javax.swing.table.TableCellRenderer

/**
 * @author Niklas Nieberler
 */

class TableBuilder(
    val table: JTable,
) {

    internal var mouseListener: MouseListener? = null
    internal val keyListener: KeyListener? = null

    fun <R : TableCellRenderer> columnRenderer(
        renderer: R,
        fromColumn: Int = 0,
        toColumn: Int = this.table.columnCount,
    ) {

    }

}