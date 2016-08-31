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

import org.technikradio.srp.filters.Filter;

/**
 * This class is used to draw on an image buffer.
 * 
 * @author doralitze
 *
 */
public class Renderer {

	private final ImageBuffer buffer;
	private Color currentDrawColor;
	private int transX = 0, transY = 0;

	public Renderer(ImageBuffer image) {
		buffer = image;
		currentDrawColor = new Color(0, 0, 0);
	}

	/**
	 * Use this method to get the current color.
	 * 
	 * @return the current drawing color
	 */
	public Color getColor() {
		return currentDrawColor;
	}

	/**
	 * Use this method to set the drawing color.
	 * 
	 * @param newDrawColor
	 *            the new color to use for drawing.
	 */
	public void setColor(Color newDrawColor) {
		this.currentDrawColor = newDrawColor;
	}

	/**
	 * Use this method in order to invoke translation.
	 * 
	 * @param x
	 *            The amount to translate on the x axis.
	 * @param y
	 *            The amount to translate on the y axis.
	 */
	public void translate(int x, int y) {
		transX += x;
		transY += y;
	}

	/**
	 * Use this method to invoke a filter on the image.
	 * 
	 * @param f
	 *            The filter to invoke.
	 */
	public void invokeFilter(Filter f) {
		invokeFilter(f, TaskProvider.getNumberOfAviableCores());
	}

	/**
	 * Use this method to invoke a filter on the image. Note that the optimum
	 * performance result will be achieved by using the
	 * {@link org.technikradio.srp.Renderer#invokeFilter(Filter)} method due to
	 * its knowledge of the system.
	 * 
	 * @param f
	 *            The filter to invoke.
	 * @param numberOfThreads
	 *            The amount of threads that the filter should use.
	 */
	public void invokeFilter(final Filter f, int numberOfThreads) {
		f.prepare(this);
		for (int i = 0; i < numberOfThreads; i++) {
			final int lowerEnd = (buffer.getHeight() / numberOfThreads) * i;
			final int upperEnd = (buffer.getHeight() / numberOfThreads) * (i + 1);
			Runnable r = new Runnable() {

				@Override
				public void run() {
					for (int y = 0; y < buffer.getWidth(); y++)
						for (int x = lowerEnd; x <= upperEnd; x++)
							f.perform(buffer, x, y);
				}
			};
			TaskProvider.handleProcessingRequest(r);
		}
	}

	/**
	 * Use this method to render a different image inside this one.
	 * 
	 * @param image
	 *            The image to render.
	 * @param x
	 *            The x coordinate where the image should be rendered.
	 * @param y
	 *            The y coordinate where the image should be rendered.
	 */
	public void drawImage(ImageBuffer image, int x, int y) {
		drawImage(image, x, y, image.getWidth(), image.getHeight(), 0, 0);
	}

	/**
	 * Use this method to render a different image inside this one.
	 * 
	 * @param image
	 *            The image to render.
	 * @param x
	 *            The x coordinate where the image should be rendered.
	 * @param y
	 *            The y coordinate where the image should be rendered.
	 * @param width
	 *            The width of the image part that should be rendered.
	 * @param height
	 *            The height of the image part that should be rendered.
	 * @param partX
	 *            The x coordinate of the image part where the rendering should
	 *            start.
	 * @param partY
	 *            The y coordinate of the image part where the rendering should
	 *            start.
	 */
	public void drawImage(ImageBuffer image, int x, int y, int width, int height, int partX, int partY) {
		// TODO implement with alpha multiplication and translation.
	}

}
