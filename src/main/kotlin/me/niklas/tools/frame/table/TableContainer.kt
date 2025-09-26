package me.niklas.tools.frame.table

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import javax.swing.AbstractAction
import javax.swing.JTable
import javax.swing.KeyStroke

/**
 * @author Niklas Nieberler
 */

class TableContainer<E : Any>(
    id: String,
    private val configurator: TableConfigurator<E>,
    val builder: TableBuilder.() -> Unit
) {

    private val table = JTable(this.configurator)
    private val tableBuilder = TableBuilder(configurator, table)

    init {
        table.tableHeader.reorderingAllowed = false
    }

    fun createTable(): JTable {
        this.table.addMouseListener(this.tableBuilder.mouseListener)
        this.table.addKeyListener(this.tableBuilder.keyListener)

        val dataRows = this.configurator.findRows
            .map { this.configurator.buildColumn(it) }
            .toTypedArray()
        this.configurator.setDataVector(dataRows, this.configurator.getHeaders())

        changeColumnWidth()
        setCopyColumnStroke()

        this.tableBuilder.builder()
        return this.table
    }

    private fun changeColumnWidth() {
        val columnWidths = this.configurator.getColumnWidths()
        columnWidths
            .filter { it.value != null }
            .forEach { entry ->
                val column = this.table.columnModel.getColumn(entry.key)
                entry.value?.let { column.preferredWidth = it }
            }
    }

    private fun setCopyColumnStroke() {
        val copyKeyStroke = KeyStroke.getKeyStroke("ctrl C")
        this.table.inputMap.put(copyKeyStroke, "copyFirstCell")

        val tableColumn = this.configurator.columns
            .firstOrNull { it.copyColumn }
        val copyColumn = tableColumn?.let { this.configurator.indexOfColumn(it) } ?: 0

        this.table.actionMap.put("copyFirstCell", object : AbstractAction() {
            override fun actionPerformed(e: java.awt.event.ActionEvent?) {
                if (table.selectedRow != -1) {
                    val firstCellValue = table.getValueAt(table.selectedRow, copyColumn)?.toString() ?: ""
                    val stringSelection = StringSelection(firstCellValue)
                    Toolkit.getDefaultToolkit().systemClipboard.setContents(stringSelection, null)
                }
            }
        })
    }

}