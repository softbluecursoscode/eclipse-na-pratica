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

import java.util.Arrays;

import br.com.softblue.jogo.board.Space.Type;
import br.com.softblue.jogo.counter.Counter;
import br.com.softblue.jogo.infrastructure.Printable;

public class Board implements Printable {

	private Space[] spaces;
	private Space spaceHome;
	private Space spaceStartHere;
	private Counter winnerCounter;

	public Board(int numSpaces) {
		spaces = new Space[numSpaces + 2];

		for (int i = 0; i < spaces.length; i++) {
			if (i == 0) {
				spaces[i] = new Space(0, Type.START_HERE);
				spaceStartHere = spaces[i];

			} else if (i == spaces.length - 1) {
				spaces[i] = new Space(i, Type.HOME);
				spaceHome = spaces[i];

			} else {
				spaces[i] = new Space(i, Type.REGULAR);
			}
		}
	}

	@Override
	public String toString() {
		return "Board [spaces=" + Arrays.toString(spaces) + "]";
	}

	@Override
	public void print() {
		System.out.println("TABULEIRO:");
		for (Space space : spaces) {
			System.out.print(space + " ");
		}
		System.out.println();
	}

	public void setupCounters(Counter[] counters) {
		for (Counter counter : counters) {
			counter.goTo(spaceStartHere);
		}
	}

	public void move(Counter counter, int diceNumber) {
		Space space = counter.getCurrentSpace();
		int newSpaceNumber = space.getNumber() + diceNumber;

		Space newSpace;

		if (newSpaceNumber >= spaceHome.getNumber()) {
			newSpace = spaceHome;
			winnerCounter = counter;

		} else {
			newSpace = spaces[newSpaceNumber];
		}

		counter.goTo(newSpace);
		System.out.format("player '%s' foi para a casa %s\n", counter.getName(), newSpace);

		Transition transition = newSpace.getTransition();

		if (transition != null) {
			System.out.format("player '%s' encontrou uma %s na casa %s\n", counter.getName(), transition.getType(),	newSpace);
			counter.goTo(transition.getSpaceTo());
			System.out.format("player '%s' foi para a casa %s\n", counter.getName(), transition.getSpaceTo());
		}
	}

	public Counter getWinnerCounter() {
		return winnerCounter;
	}

	public boolean gameFinished() {
		return winnerCounter != null;
	}

	public void addTransition(int from, int to) {
		Space spaceFrom = spaces[from];
		Space spaceTo = spaces[to];

		Transition transition = new Transition(spaceFrom, spaceTo);
		spaceFrom.setTransition(transition);
	}
}
