package me.niklas.tools.frame

import java.awt.Dimension
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.InputStream
import javax.imageio.ImageIO

/**
 * @author Niklas Nieberler
 */

object FrameToolService {

    private var bufferedImage: BufferedImage? = null
    private var frameDimension = Dimension(1540, 778)

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

    /**
     * Sets a default dimension for a [javax.swing.JFrame]
     * @param dimension default dimension
     */
    fun setFrameDimension(dimension: Dimension) {
        this.frameDimension = dimension
    }

    /**
     * Gets the default frame dimension
     */
    fun getFrameDimension(): Dimension = this.frameDimension

}