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
package br.com.softblue.jogo.core;

import br.com.softblue.jogo.board.Board;
import br.com.softblue.jogo.counter.Counter;
import br.com.softblue.jogo.counter.Counters;

/**
 * Classe que representa o jogo.
 */
public class Game {
	
	private static final int NUM_SPACES = 30;
	private static final int NUM_PLAYERS = 2;

	
	/**
	 * Inicia o jogo
	 */
	public void play() {
		// Cria o tabuleiro
		Board board = new Board(NUM_SPACES);
		addTransitions(board);
		
		board.print();
		
		Counters counters = new Counters(board, NUM_PLAYERS);
		counters.print();
		
		while (!board.gameFinished()) {
			Counter currentCounter = counters.next();
			currentCounter.play(board);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Counter winnerCounter = board.getWinnerCounter();
		System.out.format("Jogador '%s' GANHOU!\n", winnerCounter.getName());
	}

	/**
	 * Adiciona as transições
	 * @param board Tabuleiro do jogo
	 */
	private void addTransitions(Board board) {
		board.addTransition(4, 12);
		board.addTransition(7, 9);
		board.addTransition(11, 25);
		board.addTransition(14, 2);
		board.addTransition(22, 5);
		board.addTransition(28, 18);
	}
}
