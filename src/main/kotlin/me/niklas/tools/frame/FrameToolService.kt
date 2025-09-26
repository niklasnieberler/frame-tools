package me.niklas.tools.frame

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.InputStream
import javax.imageio.ImageIO

/**
 * @author Niklas Nieberler
 */

object FrameToolService {

    private var bufferedImage: BufferedImage? = null

    /**
     * Sets a icon image for all frames
     * @param inputStream image input stream
     */
    fun setIconImage(inputStream: InputStream) {
        this.bufferedImage = ImageIO.read(inputStream)
    }

    /**
     * Gets the configured icon image
     */
    fun getIconImage(): Image? = this.bufferedImage

}