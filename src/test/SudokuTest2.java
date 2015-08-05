package test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import sudoku.Sudoku2;
import sudoku.SudokuSolver2;

public class SudokuTest2 {

	ClassLoader classLoader = SudokuTest2.class.getClassLoader();

	@Test
	public void sudokuTestEasyN2() throws IOException {
		System.out.println("\nTESTE DO 4 x 4 EASY =================================================================================");
		testExample("test/Easy4x4_input.txt", "test/Easy4x4_output.txt");
	}
	
	@Test
	public void sudokuTestSimpleN2() throws IOException {
		System.out.println("\nTESTE DO 4 x 4 SIMPLE =================================================================================");
		testExample("test/Simple4x4_input.txt", "test/Simple4x4_output.txt");
	}
	
	@Test
	public void sudokuTestAverageN2() throws IOException {
		System.out.println("\nTESTE DO 4 x 4 AVERAGE =================================================================================");
		testExample("test/Average4x4_input.txt", "test/Average4x4_output.txt");
	}
	
	@Test
	public void sudokuTestMediumN2() throws IOException {
		System.out.println("\nTESTE DO 4 x 4 MEDIUM =================================================================================");
		testExample("test/Medium4x4_input.txt", "test/Medium4x4_output.txt");
	}
	
	@Test
	public void sudokuTestHardN2() throws IOException {
		System.out.println("\nTESTE DO 4 x 4 HARD =================================================================================");
		testExample("test/Hard4x4_input.txt", "test/Hard4x4_output.txt");
	}
	
	@Test
	public void sudokuTestEasyN3() throws IOException {
		System.out.println("\nTESTE DO 9 x 9 EASY ===================================================================================");
		testExample("test/Easy_input.txt", "test/Easy_output.txt");
	}
	
	@Test
	public void sudokuTestNormalN3() throws IOException {
		System.out.println("\nTESTE DO 9 x 9 NORMAL ================================================================================");
		testExample("test/Normal_input.txt", "test/Normal_output.txt");
	}
	
	@Test
	public void sudokuTestHardN3() throws IOException {
		System.out.println("\nTESTE DO 9 x 9 HARD =====================================================================================");
		testExample("test/Hard_input.txt", "test/Hard_output.txt");
	}
	
	@Test
	public void sudokuTestSuperHardN3() throws IOException {
		System.out.println("\nTESTE DO 9 x 9 SUPERHARD =====================================================================================");
		testExample("test/SuperHard_input.txt", "test/SuperHard_output.txt");
	}
	
	/*
	@Test
	public void sudokuTestEmptyN3() throws IOException {
		System.out.println("\nTESTE DO 9 x 9 VAZIO =====================================================================================");
		testExample("test/Empty_input.txt", "test/Empty_output.txt");
	}
	
	@Test
	public void sudokuTestEmptyN4() throws IOException {
		System.out.println("\nTESTE DO 16 x 16 VAZIO =====================================================================================");
		testExample("test/Empty16x16_input.txt", "test/Empty16x16_output.txt");
	}
	*/
	
	public void testExample(String inputFile, String outputFile) throws IOException{
		
		String fileIn = classLoader.getResource(inputFile).getFile();
		Sudoku2 sudokuIn = Sudoku2.readSudoku(fileIn);

		String fileOut = classLoader.getResource(outputFile).getFile();
		Sudoku2 sudokuOut = Sudoku2.readSudoku(fileOut);
		
		SudokuSolver2 solver = new SudokuSolver2(sudokuIn);
		Sudoku2 sudokuSolution = solver.solve();
		Assert.assertEquals(sudokuOut, sudokuSolution);
	}
	
	@Test
	public void heuristicForSolutions() throws IOException {
		heuristicTest(0, "test/Beginners_output.txt");
		heuristicTest(0, "test/Easy_output.txt");
		heuristicTest(0, "test/Hard_output.txt");
		heuristicTest(0, "test/Normal_output.txt");
		heuristicTest(0, "test/SuperHard_output.txt");
		heuristicTest(0, "test/Average4x4_output.txt");
		heuristicTest(0, "test/Easy4x4_output.txt");
		heuristicTest(0, "test/Hard4x4_output.txt");
		heuristicTest(0, "test/Medium4x4_output.txt");
		heuristicTest(0, "test/Simple4x4_output.txt");
	}

	@Test
	public void heuristicForNonSolutions() throws IOException {
		heuristicTest(2, "test/Easy_output_2Conflict.txt");
		heuristicTest(6, "test/SuperHard_output_6Conflicts.txt");
	}
	
	private void heuristicTest(int value, String path) throws IOException {
		String solutionFile1 = classLoader.getResource(path).getFile();
		Sudoku2 sudokuSolution1 = Sudoku2.readSudoku(solutionFile1);
		Assert.assertEquals(value, SudokuSolver2.heuristic(sudokuSolution1));
	}
}
