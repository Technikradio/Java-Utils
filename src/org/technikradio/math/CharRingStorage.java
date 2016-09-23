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

package org.technikradio.math;

/**
 * @author doralitze This class represents a ring storage designed for char
 *         values.
 */
public class CharRingStorage {

	private int pointer;
	private int length;
	private char data[];

	/**
	 * This constructor generates a new ring buffer.
	 * 
	 * @param length
	 *            The length of the buffer
	 */
	public CharRingStorage(int length) {
		if (length < 1)
			throw new RuntimeException("The buffer needs to be at least 1 long.");
		this.length = length;
		pointer = 0;
		data = new char[length];
	}

	/**
	 * Use this method in order to put a new value inside the buffer
	 * 
	 * @param v
	 *            The value to put inside the buffer.
	 */
	public void put(char v) {
		tick();
		data[pointer] = v;
	}

	/**
	 * Use this function in order to get a value from the ring buffer.
	 * 
	 * @param position
	 *            The desired elements position.
	 * @return The value of the desired element.
	 */
	public char get(int position) {
		return data[position];
	}

	/**
	 * This method resets the data to the value 0.
	 */
	public void reset() {
		reset((char) 0);
	}

	/**
	 * This method resets the data to a desired value.
	 * 
	 * @param number
	 *            The value to set the data to.
	 */
	public synchronized void reset(char number) {
		for (int i = 0; i < length; i++) {
			data[i] = number;
		}
		pointer = 0;
	}

	/**
	 * This method sets the pointer to the next position.
	 */
	public void tick() {
		pointer++;
		if (!(pointer < length)) {
			pointer = 0;
		}
	}

	/**
	 * Use this method in order to get the pointer
	 * 
	 * @return the pointer
	 */
	public int getPointer() {
		return pointer;
	}

	/**
	 * Use this method in order to set the pointer to a new position. NOTE: This
	 * method is synchronized. If you just want to set the pointer to the next
	 * element use the tick function.
	 * 
	 * @param pointer
	 *            the value to set the pointer to.
	 */
	public synchronized void setPointer(int pointer) {
		this.pointer = pointer;
	}

	/**
	 * Use this method in order to retrieve the length of the buffer.
	 * @return the length of the buffer.
	 */
	public int getLength() {
		return length;
	}

}
