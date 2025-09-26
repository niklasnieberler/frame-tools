package me.niklas.tools.frame.table.renderer

import java.awt.Color
import java.awt.Graphics
import javax.swing.table.DefaultTableCellRenderer

/**
 * @author Niklas Nieberler
 */

abstract class TableColumnRenderer(
    private val paintComponents: Boolean = false
) : DefaultTableCellRenderer() {

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        if (!this.paintComponents)
            return
        g.color = Color(230, 230, 230)
        g.drawRect(0, height -1, width, height - 1)
    }

}