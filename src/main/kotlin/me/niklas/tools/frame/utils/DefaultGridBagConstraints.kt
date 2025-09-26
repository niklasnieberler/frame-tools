package me.niklas.tools.frame.utils

import java.awt.GridBagConstraints
import java.awt.Insets

/**
 * @author Niklas Nieberler
 */

class DefaultGridBagConstraints : GridBagConstraints() {

    init {
        gridx = 0
        gridy = 0
        gridheight = 3
        insets = Insets(0, 0, 0, 0)
        fill = BOTH
        weightx = 0.3
        weighty = 1.0
    }

}