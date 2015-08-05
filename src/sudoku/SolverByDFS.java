package sudoku;

public class SolverByDFS {
	private int sizeOfProblem;
	public SudokuDFS useDFS(SudokuDFS problem) {
		sizeOfProblem = problem.getSize();
		if(solve(0, 0, problem.getMatrix()))
			return problem;
		else 
			return null;
	}
	
	public boolean solve(int i, int j, int[][] cells) {
		if (i == sizeOfProblem) {
			i = 0;
			if(++j == sizeOfProblem)
				return true;
		}
		if(cells[i][j] != 0) //skip filled cells
			return solve(i+1, j, cells);
		for (int val=1; val<=9; ++val) {
			if(legal(i,j,val,cells)) {
				cells[i][j] = val;
				if(solve(i+1,j,cells))
					return true;
			}
		}
		cells[i][j] = 0; //reset on backtrack
		return false;
	}
	
	private boolean legal(int i, int j, int val, int[][] cells) {
		for (int k = 0; k<sizeOfProblem; ++k) //row
			if(val == cells[k][j])
				return false;
		for (int k = 0; k < sizeOfProblem; ++k) //col
			if(val == cells[i][k])
				return false;
		int rad = (int)Math.sqrt(sizeOfProblem);
		int boxRowOffset = (i/rad)*rad;
		int boxColOffset = (j/rad)*rad;
		for(int k = 0; k < rad; ++k) //box
			for(int m = 0; m < rad; ++m)
				if(val == cells[boxRowOffset+k][boxColOffset+m])
					return false;
		return true; // no violations, so it's legal
	}
}
