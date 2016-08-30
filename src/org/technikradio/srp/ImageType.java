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
 * This enumeration contains a set of different possible image types.
 * @author doralitze
 *
 */
public enum ImageType {
	/**
	 * This image type defines an image that only contains gray scales reaching from 0 to 255.
	 */
	GRAY_SCALE,
	/**
	 * This image type defines an image that only contains three channels with an 8 bit depth.
	 */
	RGB,
	/**
	 * This image type contains a fourth channel reserved for the alpha values.
	 */
	RGBA,
	/**
	 * This image type contains three 24bit channels reserved for red, green and blue.
	 */
	sRGB,
	/**
	 * This image type specifies an 24bit RGBA image.
	 */
	sRGBA,
	/**
	 * This image type specifies an 32bit RGB image.
	 */
	hrRGB,
	/**
	 * This image type specifies an 32bit RGBA image.
	 */
	hrRGBA,
	/**
	 * This image type specifies a single channel 32bit image.
	 */
	hrGRAY_SCALE;
	
	/**
	 * Use this method to get the number of represented channels inside this image type.
	 * @param type The type to check
	 * @return The number of channels
	 */
	public static int getChannels(ImageType type){
		switch(type){
		case GRAY_SCALE:
		case hrGRAY_SCALE:
			return 1;
		case RGBA:
		case hrRGBA:
		case sRGBA:
			return 4;
		case hrRGB:
		case sRGB:
		case RGB:
			return 3;
		}
		return 0;
	}
	
	/**
	 * Use this method to get the number of represented channels inside this image type.
	 * @return The number of channels
	 */
	public int getChannels(){
		switch(this){
		case GRAY_SCALE:
		case hrGRAY_SCALE:
			return 1;
		case RGBA:
		case hrRGBA:
		case sRGBA:
			return 4;
		case hrRGB:
		case sRGB:
		case RGB:
			return 3;
		}
		return 0;
	}
	
	/**
	 * Use this method to get the number of bits represented inside the image type.
	 * @param type The type to check
	 * @return The bit depth of the image type.
	 */
	public static int getBitDeph(ImageType type){
		switch(type){
		case GRAY_SCALE:
		case RGB:
		case RGBA:
			return 8;
		case hrGRAY_SCALE:
		case hrRGB:
		case hrRGBA:
			return 32;
		case sRGB:
		case sRGBA:
			return 24;
		}
		return 0;
	}
	
	/**
	 * Use this method to get the number of bits represented inside the image type.
	 * @param type The type to check
	 * @return The bit depth of the image type.
	 */
	public int getBitDeph(){
		switch(this){
		case GRAY_SCALE:
		case RGB:
		case RGBA:
			return 8;
		case hrGRAY_SCALE:
		case hrRGB:
		case hrRGBA:
			return 32;
		case sRGB:
		case sRGBA:
			return 24;
		}
		return 0;
	}
}
