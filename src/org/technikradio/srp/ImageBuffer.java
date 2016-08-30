/*
Copyright (c) 2016, Technikradio
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

* Neither the name of Node2 nor the names of its
  contributors may be used to endorse or promote products derived from
  this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * 
 */
package org.technikradio.srp;

/**
 * This class is used to hold the data of an image.
 * 
 * @author doralitze
 *
 */
public class ImageBuffer {

	protected static final int MAX_32bit_NUM = 2_147_483_647; // TOTAL of 31bit
																// due to sign
	protected static final int MAX_24bit_NUM = 8388607;

	private final int width, height;
	protected int[][][] data;
	private final ImageType type;

	/**
	 * This constructor creates a new instance of an image buffer.
	 * 
	 * @param width
	 *            The with of the image to store.
	 * @param height
	 *            The height of the image to store.
	 * @param type
	 *            The type of the desired image buffer.
	 */
	public ImageBuffer(int width, int height, ImageType type) {
		this.type = type;
		this.width = width;
		this.height = height;
		data = new int[this.width][this.height][this.type.getChannels()];
	}

	/**
	 * This constructor creates a new instance of an image buffer with an
	 * specified background.
	 * 
	 * @param width
	 *            The with of the image to store.
	 * @param height
	 *            The height of the image to store.
	 * @param type
	 *            The type of the desired image buffer.
	 * @param backgroundColor
	 *            The desired color used to fill the background.
	 */
	public ImageBuffer(int width, int height, ImageType type, Color backgroundColor) {
		this(width, height, type);
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				setPixelAt(x, y, backgroundColor);
			}
		}
	}

	/**
	 * Use this method to get the width of the stored image.
	 * 
	 * @return the width of the stored image.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Use this method to get the width of the stored image.
	 * 
	 * @return the height of the stored image.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Use this method to get the ImageType of the stored image.
	 * 
	 * @return the type of the stored image.
	 */
	public ImageType getType() {
		return type;
	}

	public Color getPixelAt(int x, int y) {
		switch (this.type) {
		case GRAY_SCALE:
			return new Color(mapUp(data[x][y][0]), mapUp(data[x][y][0]), mapUp(data[x][y][0]));
		case RGB:
			return new Color(mapUp(data[x][y][0]), mapUp(data[x][y][1]), mapUp(data[x][y][2]));
		case RGBA:
			return new Color(mapUp(data[x][y][0]), mapUp(data[x][y][1]), mapUp(data[x][y][2]), mapUp(data[x][y][3]));
		case hrGRAY_SCALE:
			return new Color(mapUp(data[x][y][0]), mapUp(data[x][y][0]), mapUp(data[x][y][0]));
		case hrRGB:
			return new Color(mapUp(data[x][y][0]), mapUp(data[x][y][1]), mapUp(data[x][y][2]));
		case hrRGBA:
			return new Color(mapUp(data[x][y][0]), mapUp(data[x][y][1]), mapUp(data[x][y][2]), mapUp(data[x][y][3]));
		case sRGB:
			return new Color(mapUp(data[x][y][0]), mapUp(data[x][y][1]), mapUp(data[x][y][2]));
		case sRGBA:
			return new Color(mapUp(data[x][y][0]), mapUp(data[x][y][1]), mapUp(data[x][y][2]), mapUp(data[x][y][3]));
		default:
			return new Color();
		}
	}

	private int mapUp(int i) {
		switch (this.type.getBitDeph()) {
		case 8:
			return (int) ((i / 255) * MAX_32bit_NUM);
		case 24:
			return (int) ((i / MAX_24bit_NUM) * MAX_32bit_NUM);
		case 32:
		default:
			return i;
		}
	}

	public void setPixelAt(int x, int y, Color c) {
		switch (this.type) {
		case GRAY_SCALE:
		case hrGRAY_SCALE:
			data[x][y][0] = mapDown(
					Math.sqrt((Math.pow(c.getRed(), 2) + Math.pow(c.getGreen(), 2) + Math.pow(c.getBlue(), 2)) / 3));
			break;
		case RGB:
		case hrRGB:
		case sRGB:
			data[x][y][0] = mapDown(c.getRed());
			data[x][y][1] = mapDown(c.getGreen());
			data[x][y][2] = mapDown(c.getBlue());
			break;
		case RGBA:
		case sRGBA:
		case hrRGBA:
			data[x][y][0] = mapDown(c.getRed());
			data[x][y][1] = mapDown(c.getGreen());
			data[x][y][2] = mapDown(c.getBlue());
			data[x][y][3] = mapDown(c.getAlpha());
			break;
		default:
			break;

		}
	}

	private int mapDown(double i) {
		switch (this.type.getBitDeph()) {
		case 8:
			return (int) ((i / MAX_32bit_NUM) * 255);
		case 24:
			return (int) ((i / MAX_32bit_NUM) * MAX_24bit_NUM);
		case 32:
		default:
			return (int) ((i / MAX_32bit_NUM) * 255);
		}
	}

	/**
	 * Use this method to get a part image of the main image.
	 * 
	 * @param x
	 *            The x coordinate to start from.
	 * @param y
	 *            The y coordinate to start from.
	 * @param width
	 *            The desired with of the image part.
	 * @param height
	 *            The desired height of the image part.
	 * @return The computed image part.
	 */
	public ImageBuffer getPartImage(int x, int y, int width, int height) {
		ImageBuffer ib = new ImageBuffer(width, height, this.type);
		for (int cx = x; cx < x + width && cx < this.width; cx++) {
			for (int cy = y; cy < y + height && cy < this.height; cy++) {
				for (int t = 0; t < data[cx][cy].length; t++) {
					ib.data[cx][cy][t] = data[cx][cy][t];
				}
			}
		}
		return ib;
	}

	/**
	 * Use this method to get a part image of the main image but of a different
	 * type.<br/>
	 * Warning using this method instead of computing an image of the same type
	 * may require way more time due to color space conversion.
	 * 
	 * @param x
	 *            The x coordinate to start from.
	 * @param y
	 *            The y coordinate to start from.
	 * @param width
	 *            The desired with of the image part.
	 * @param height
	 *            The desired height of the image part.
	 * @param type
	 *            The desired type of the image part.
	 * @return The computed image part.
	 */
	public ImageBuffer getPartImage(int x, int y, int width, int height, ImageType type) {
		if (type.equals(this.type))
			return getPartImage(x, y, width, height);
		ImageBuffer ib = new ImageBuffer(width, height, type);
		for (int cx = x; cx < x + width && cx < this.width; cx++) {
			for (int cy = y; cy < y + height && cy < this.height; cy++) {
				ib.setPixelAt(cx, cy, this.getPixelAt(cx, cy));
			}
		}
		return ib;
	}

	// TODO implement file saving
}
