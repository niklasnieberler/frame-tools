package me.niklas.tools.frame.table

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.JTable
import javax.swing.KeyStroke

/**
 * @author Niklas Nieberler
 */

class TableColumnCopyStroke<E : Any>(
    private val table: JTable,
    private val configurator: TableConfigurator<E>
) {

    private val copyKeyStroke = KeyStroke.getKeyStroke("ctrl C")

    fun setStrokeIntoTable() {
        this.table.inputMap.put(copyKeyStroke, "copyFirstCell")
        this.table.actionMap.put("copyFirstCell", handleAction())
    }

    private fun handleAction() = object : AbstractAction() {
        override fun actionPerformed(e: ActionEvent?) {
            if (table.selectedRow != -1) {
                val firstCellValue = table.getValueAt(table.selectedRow, getColumnToCopy())?.toString() ?: ""
                val stringSelection = StringSelection(firstCellValue)
                Toolkit.getDefaultToolkit().systemClipboard
                    .setContents(stringSelection, null)
            }
        }
    }

    private fun getColumnToCopy(): Int {
        val tableColumn = this.configurator.columns
            .firstOrNull { it.copyColumn }
        return tableColumn?.let { this.configurator.indexOfColumn(it) } ?: 0
    }

}