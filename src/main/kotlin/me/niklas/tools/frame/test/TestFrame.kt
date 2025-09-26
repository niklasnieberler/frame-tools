package me.niklas.tools.frame.test

import me.niklas.tools.frame.Frame
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

/**
 * @author Niklas Nieberler
 */

class TestFrame : Frame(
    ""
) {

    init {
        tableScrollPane("", TestTableConfigurator()) {

        }
    }

}