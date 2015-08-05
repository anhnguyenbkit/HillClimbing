import java.io.IOException;
import java.util.Scanner;

import sudoku.Sudoku;
import sudoku.Sudoku2;
import sudoku.SudokuSolver;
import sudoku.SudokuSolver2;

public class Main {

	public static void main(String[] args) {
		try {
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Entrada: ");
			String path = scanner.nextLine();
			Sudoku sudoku = Sudoku.readSudoku(path);
			
			System.out.println("Resolução pelo Hill Climbing: ");
			SudokuSolver solver = new SudokuSolver(sudoku);
			solver.solve();
			
			Sudoku2 sudoku2 = Sudoku2.readSudoku(path);
			System.out.println("\n\nResolução pelo Hill Climbing com Constraint Propagation: ");
			SudokuSolver2 solver2 = new SudokuSolver2(sudoku2);
			solver2.solve();
			
			scanner.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}
	
}
