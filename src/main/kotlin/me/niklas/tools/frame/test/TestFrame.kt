package me.niklas.tools.frame.test

import me.niklas.tools.frame.Frame
import me.niklas.tools.frame.table.renderer.AlignmentTableColumnRenderer

/**
 * @author Niklas Nieberler
 */

class TestFrame : Frame(
    ""
) {

    init {
        tableScrollPane("", TestTableConfigurator()) {
            columnRenderer(AlignmentTableColumnRenderer(1))
        }
    }

}