import java.io.IOException;
import java.util.Scanner;

import sudoku.SolverByBFS;
import sudoku.SolverByDFS;
import sudoku.Sudoku;
import sudoku.Sudoku2;
import sudoku.SudokuBFS;
import sudoku.SudokuDFS;
import sudoku.SudokuSolver;
import sudoku.SudokuSolver2;

public class Main {

	private static final long MEGABYTE = 1024L * 1024L;
	
	public static void main(String[] args) {
		SudokuBFS stare_fin = null;
		SudokuDFS st_fin = null;
		long startTime;
		long endTime;
		long totalTime;
		Runtime runtime;
		long memory;
		try {
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("input: ");
			String path = scanner.nextLine();
			Sudoku sudoku = Sudoku.readSudoku(path);
			
			System.out.println("Resolution by Hill Climbing: ");
			
			SudokuSolver solver = new SudokuSolver(sudoku);
			startTime = System.currentTimeMillis();
			solver.solve();
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			System.out.println("Run time solver hill 1: "+totalTime);
			// Get the Java runtime
		    runtime = Runtime.getRuntime();
		    // Run the garbage collector
		    runtime.gc();
		    // Calculate the used memory
		    memory = runtime.totalMemory() - runtime.freeMemory();
		    System.out.println("Hill 1 Used memory is bytes: " + memory);
		    System.out.println("Hill 1 Used memory is megabytes: "
		        + bytesToMegabytes(memory));
			
			Sudoku2 sudoku2 = Sudoku2.readSudoku(path);
			System.out.println("\n\nResolution by Hill Climbing with Constraint Propagation: ");
			SudokuSolver2 solver2 = new SudokuSolver2(sudoku2);
			startTime = System.currentTimeMillis();
			solver2.solve();
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			System.out.println("Run time solver hill 2: "+totalTime);
			// Get the Java runtime
		    runtime = Runtime.getRuntime();
		    // Run the garbage collector
		    runtime.gc();
		    // Calculate the used memory
		    memory = runtime.totalMemory() - runtime.freeMemory();
		    System.out.println("Hill 2 Used memory is bytes: " + memory);
		    System.out.println("Hill 2 Used memory is megabytes: "
		        + bytesToMegabytes(memory));
			
			
			SolverByBFS joc = new SolverByBFS();
			SudokuBFS stare_ini = new SudokuBFS(path);
			startTime = System.currentTimeMillis();
			stare_fin = joc.searchMethod(stare_ini);
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			System.out.println("Run time solver BFS: "+totalTime);
			// Get the Java runtime
		    runtime = Runtime.getRuntime();
		    // Run the garbage collector
		    runtime.gc();
		    // Calculate the used memory
		    memory = runtime.totalMemory() - runtime.freeMemory();
		    System.out.println("BFS Used memory is bytes: " + memory);
		    System.out.println("BFS Used memory is megabytes: "
		        + bytesToMegabytes(memory));
			
			if(stare_fin != null)
			{
				System.out.println("Find solution BFS: ");
				System.out.println(stare_fin.toString());
			}
			
			SolverByDFS dfs = new SolverByDFS();
			SudokuDFS dfs_ini = new SudokuDFS(path);
			startTime = System.currentTimeMillis();
			st_fin = dfs.useDFS(dfs_ini);
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			System.out.println("Run time solver DFS: "+totalTime);
			// Get the Java runtime
		    runtime = Runtime.getRuntime();
		    // Run the garbage collector
		    runtime.gc();
		    // Calculate the used memory
		    memory = runtime.totalMemory() - runtime.freeMemory();
		    System.out.println("DFS Used memory is bytes: " + memory);
		    System.out.println("DFS Used memory is megabytes: "
		        + bytesToMegabytes(memory));
			
			if(st_fin != null)
			{
				System.out.println("Find solution DFS: ");
				System.out.println(st_fin.toString());
			}
			
			scanner.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}
	
	public static long bytesToMegabytes(long bytes) {
	    return bytes / MEGABYTE;
	  }
	
}
