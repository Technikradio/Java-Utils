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
 * This class represents a pixel color.
 * Aldough the corresponding image buffer bight be of an lower image type tier the color represents the maximum possible quality.
 * @author doralitze
 *
 */
public class Color {
	private int r,g,b,a;
	
	/**
	 * This constructor creates a white without transparency.
	 */
	public Color(){
		super();
		this.r = 255;
		this.g = 255;
		this.b = 255;
		this.a = 255;
	}

	/**
	 * This constructor initializes a new color with a default alpha value of 255.
	 * @param r The red component of the color.
	 * @param g The green component of the color.
	 * @param b The blue component of the color.
	 */
	public Color(int r, int g, int b) {
		super();
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 255;
	}

	/**
	 * This constructor initializes a new color.
	 * @param r The red component of the color.
	 * @param g The green component of the color.
	 * @param b The blue component of the color.
	 * @param a The alpha (transparency) component of the color.
	 */
	public Color(int r, int g, int b, int a) {
		super();
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	/**
	 * Use this method to get the red component of the color
	 * @return the r component of the color.
	 */
	public int getRed() {
		return r;
	}

	/**
	 * Use this method to set the red component of the color.
	 * @param r the red value to set
	 */
	public void setRed(int r) {
		this.r = r;
	}

	/**
	 * Use this method to get the green component of the color
	 * @return the g component of the color.
	 */
	public int getGreen() {
		return g;
	}

	/**
	 * Use this method to set the green component of the color.
	 * @param g the green value to set
	 */
	public void setGreen(int g) {
		this.g = g;
	}

	/**
	 * Use this method to get the blue component of the color
	 * @return the b component of the color.
	 */
	public int getBlue() {
		return b;
	}

	/**
	 * Use this method to set the blue component of the color.
	 * @param b the blue value to set
	 */
	public void setBlue(int b) {
		this.b = b;
	}

	/**
	 * Use this method to get the alpha component of the color
	 * @return the transparency component of the color.
	 */
	public int getAlpha() {
		return a;
	}

	/**
	 * Use this method to set the alpha component of the color.
	 * @param a the alpha value to set
	 */
	public void setAlpha(int a) {
		this.a = a;
	}
	
}
