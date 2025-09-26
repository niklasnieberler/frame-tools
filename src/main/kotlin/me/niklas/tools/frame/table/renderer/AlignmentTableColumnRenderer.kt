package me.niklas.tools.frame.table.renderer

import java.awt.Component
import javax.swing.JLabel
import javax.swing.JTable

/**
 * @author Niklas Nieberler
 */

class AlignmentTableColumnRenderer(
    private val columnAlignment: Int = 2,
    paintComponents: Boolean = false
) : TableColumnRenderer(paintComponents) {

    override fun getTableCellRendererComponent(
        table: JTable?,
        value: Any?,
        isSelected: Boolean,
        hasFocus: Boolean,
        row: Int,
        column: Int
    ): Component {
        val component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column)
        (component as JLabel).horizontalAlignment = getHorizontalAlignment(column)
        return component
    }

    private fun getHorizontalAlignment(column: Int): Int {
        return if (column >= this.columnAlignment) {
            RIGHT
        } else {
            LEFT
        }
    }

}