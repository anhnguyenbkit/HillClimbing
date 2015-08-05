package sudoku;

import java.util.Random;

public class SudokuSolver {
	
	Sudoku initialSudoku;
	
	public SudokuSolver(Sudoku sudoku) {
		this.initialSudoku = sudoku;
	}

	public static int heuristic(Sudoku state) {
		int conflicts = 0;
		
		// Varre cada coluna
		for (int i = 0; i < state.sizeSquare; i++) {
			int[] freq = new int[state.sizeSquare];
			for (int j = 0; j < state.sizeSquare; j++) {
				if (++freq[state.matrix[j][i] - 1] > 1)
					conflicts++;
			}
		}
		
		// Varre cada quadrado
		for (int i = 0; i < state.size; i++) {
			for (int j = 0; j < state.size; j++) {
				int[] freq = new int[state.sizeSquare];
				for (int k = state.size*i; k < state.size*(i+1); k++) {
					for (int l = state.size*j; l < state.size*(j+1); l++) {
						if (++freq[state.matrix[k][l] - 1] > 1)
							conflicts++;
					}
				}
			}
		}
		
		return conflicts;
	}
	
	public Sudoku getInitialState(Sudoku sudoku) {
		Random random = new Random();
		Sudoku initialState = sudoku.clone();
		
		for (int i = 0; i < initialState.sizeSquare; i++) {
			boolean[] used = new boolean[initialState.sizeSquare];
			for (int j = 0; j < initialState.sizeSquare; j++) {
				if (initialState.matrix[i][j] != 0)
					used[initialState.matrix[i][j] - 1] = true;
			}
			for (int j = 0; j < initialState.sizeSquare; j++) {
				if (initialState.matrix[i][j] == 0) {
					int val = random.nextInt(initialState.sizeSquare);
					while (used[val])
						val = random.nextInt(initialState.sizeSquare);
					used[val] = true;
					initialState.matrix[i][j] = val + 1;
				}
			}
		}
		
		return initialState;
	}

	public Sudoku solve() {
		System.out.println("Problema:");
		System.out.println(initialSudoku);
		
		Sudoku sudoku = null;
		int countLoops = 0;

		while (true) {
			++countLoops;
			
			sudoku = getInitialState(initialSudoku);
			int conflicts = heuristic(sudoku);
			
			while (conflicts != 0) {
				for (int i = 0; i < sudoku.sizeSquare; i++) {
					for (int j = 0; j < sudoku.sizeSquare; j++) {
						if (!sudoku.fixed[i][j]) {
							int minConflicts = Integer.MAX_VALUE;
							Sudoku bestSudoku = null;
							
							for (int k = 0; k < sudoku.sizeSquare; k++) {
								if (!sudoku.fixed[i][k]) {
									Sudoku tmpSudoku = sudoku.clone();
									int tmpVal = tmpSudoku.matrix[i][j];
									tmpSudoku.matrix[i][j] = tmpSudoku.matrix[i][k];
									tmpSudoku.matrix[i][k] = tmpVal;
									
									int tmpConflicts = heuristic(tmpSudoku);
									if (tmpConflicts < minConflicts) {
										minConflicts = tmpConflicts;
										bestSudoku = tmpSudoku;
									}
								}
							}
							
							sudoku = bestSudoku;
						}
					}
				}
				
				int actualConflicts = heuristic(sudoku);
				if (actualConflicts < conflicts)
					conflicts = actualConflicts;
				else
					break;
			}
			
			if (conflicts == 0) {
				System.out.println("Solução:");
				System.out.println(sudoku);
				if (countLoops == 1) {
					System.out.println("Precisou rodar o algoritmo " + countLoops + " vez.");					
				} else {
					System.out.println("Precisou rodar o algoritmo " + countLoops + " vezes.");
				}
				break;
			}
		}
		
		return sudoku;
	}

}
