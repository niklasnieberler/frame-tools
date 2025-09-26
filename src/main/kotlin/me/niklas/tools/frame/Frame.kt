package me.niklas.tools.frame

import me.niklas.tools.frame.table.Table
import me.niklas.tools.frame.table.TableBuilder
import me.niklas.tools.frame.table.TableConfigurator
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.table.DefaultTableModel

/**
 * @author Niklas Nieberler
 */

abstract class Frame(
    title: String,
    dimension: Dimension = Dimension(1540, 778)
) : JFrame(
    title
) {

    init {
        size = dimension
        iconImage = FrameToolService.getIconImage()
        layout = GridBagLayout()
        isFocusable = true
        setLocationRelativeTo(null)
    }

    fun <E : Any> table(id: String, configurator: TableConfigurator<E>, builder: TableBuilder.() -> Unit) {
        val tableModel = DefaultTableModel()
        val table = JTable(tableModel)

        val array = configurator.findRows
            .map { configurator.buildColumn(it) }
            .toTypedArray()

        val tableBuilder1 = TableBuilder()

        tableModel.setDataVector(array, configurator.headers.toTypedArray())

        val scrollPane = JScrollPane(table)
        scrollPane.preferredSize = Dimension(300, scrollPane.preferredSize.height)
        add(scrollPane, GridBagConstraints())
    }

}
