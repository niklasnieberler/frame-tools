package me.niklas.tools.frame

import me.niklas.tools.frame.table.TableBuilder
import me.niklas.tools.frame.table.TableConfigurator
import me.niklas.tools.frame.table.TableContainer
import me.niklas.tools.frame.utils.DefaultGridBagConstraints
import java.awt.Dimension
import java.awt.GridBagLayout
import javax.swing.JFrame
import javax.swing.JScrollPane

/**
 * @author Niklas Nieberler
 */

abstract class Frame(
    title: String,
    dimension: Dimension = FrameToolService.getFrameDimension()
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
        val tableContainer = TableContainer(id, configurator, builder)
        add(tableContainer.createTable(), DefaultGridBagConstraints())
    }

    fun <E : Any> tableScrollPane(id: String, configurator: TableConfigurator<E>, builder: TableBuilder.() -> Unit) {
        val tableContainer = TableContainer(id, configurator, builder)
        val scrollPane = JScrollPane(tableContainer.createTable())
        scrollPane.preferredSize = Dimension(300, scrollPane.preferredSize.height)
        add(scrollPane, DefaultGridBagConstraints())
    }

    fun setVisible() {
        this.isVisible = true
    }

    fun updateVisible() {
        this.isVisible != this.isVisible
    }

}
