package me.niklas.tools.frame.test

import me.niklas.tools.frame.Frame

/**
 * @author Niklas Nieberler
 */

class TestFrame : Frame(
    ""
) {

    init {
        table("", TestTableConfigurator()) {

        }
    }

}