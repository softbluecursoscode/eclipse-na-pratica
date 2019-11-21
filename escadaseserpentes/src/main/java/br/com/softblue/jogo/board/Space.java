/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2019 Softblue
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package br.com.softblue.jogo.board;

public class Space {

	public enum Type {
		START_HERE, HOME, REGULAR;
	}
	
	private int number;
	private Type type;
	private Transition transition;
	
	public Space(int number, Type type) {
		this.number = number;
		this.type = type;
	}

	public int getNumber() {
		return number;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setTransition(Transition transition) {
		this.transition = transition;
	}
	
	public Transition getTransition() {
		return transition;
	}
	
	@Override
	public String toString() {
		if (type == Type.REGULAR) {
			return String.valueOf(number);
		} else {
			return number + " (" + type + ")";
		}
	}
}
