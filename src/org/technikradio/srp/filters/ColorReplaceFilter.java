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
package org.technikradio.srp.filters;

import org.technikradio.srp.Color;
import org.technikradio.srp.ImageBuffer;
import org.technikradio.srp.Renderer;

/**
 * This filter is usable to search for specific colors and replace them with
 * other colors.
 * 
 * @author doralitze
 *
 */
public class ColorReplaceFilter extends Filter {

	private final Color search, replace;
	private int toleranz = 0;
	private boolean checkAlpha = true;

	/**
	 * Use this method to check if the filter compares the alpha channel.
	 * 
	 * @return the value of the checkAlpha flag
	 */
	public boolean isCheckAlphaEnabled() {
		return checkAlpha;
	}

	/**
	 * Use this method to set the filters check alpha flag. If this flag is set
	 * to true (the default value) the filter will also compare the alpha
	 * values.
	 * 
	 * @param checkAlpha
	 *            the checkAlpha value to set
	 */
	public void setCheckAlpha(boolean checkAlpha) {
		this.checkAlpha = checkAlpha;
	}

	/**
	 * This constructor initializes a new instance.
	 */
	public ColorReplaceFilter(Color searchColor, Color replaceColor) {
		super();
		search = searchColor;
		replace = replaceColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.technikradio.srp.filters.Filter#prepare(org.technikradio.srp.
	 * Renderer)
	 */
	@Override
	public void prepare(Renderer r) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.technikradio.srp.filters.Filter#perform(org.technikradio.srp.
	 * ImageBuffer, int, int)
	 */
	@Override
	public void perform(ImageBuffer data, int x, int y) {
		if(fits(data.getPixelAt(x, y), search))
			data.setPixelAt(x, y, replace);
	}

	/**
	 * Use this method to get the color that this filter looks for.
	 * 
	 * @return the search color
	 */
	public Color getSearchColor() {
		return search;
	}

	/**
	 * Use this method to get the color that this filter wants to replace.
	 * 
	 * @return the replacement color
	 */
	public Color getReplaceColor() {
		return replace;
	}

	private boolean fits(Color c1, Color c2) {
		if (Color.convert32to8bit(c1.getRed()) - Color.convert32to8bit(c2.getRed()) > toleranz)
			return false;
		if (Color.convert32to8bit(c1.getBlue()) - Color.convert32to8bit(c2.getBlue()) > toleranz)
			return false;
		if (Color.convert32to8bit(c1.getGreen()) - Color.convert32to8bit(c2.getGreen()) > toleranz)
			return false;
		if ((Color.convert32to8bit(c1.getAlpha()) - Color.convert32to8bit(c2.getAlpha()) > toleranz)
				&& isCheckAlphaEnabled())
			return false;
		return true;
	}

}
