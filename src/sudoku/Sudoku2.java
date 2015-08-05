package sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sudoku2 extends Sudoku {

	public boolean[][][] availableValuesMatrix;
	
	public Sudoku2(int n) {
		super(n);
		this.availableValuesMatrix = new boolean[sizeSquare][sizeSquare][sizeSquare];
		
		for (int i = 0; i < this.sizeSquare; i++)
			for (int j = 0; j < this.sizeSquare; j++)
				for (int k = 0; k < this.sizeSquare; k++)
					this.availableValuesMatrix[i][j][k] = true;
						
	}
	
	public static Sudoku2 readSudoku(String fileName) throws IOException {
		FileReader fileReader = new FileReader(fileName);
		BufferedReader input = new BufferedReader(fileReader);
		
		String inputLine = input.readLine();
		String[] split = inputLine.split(" ");
		if (split.length != 1) {
			input.close();
			throw new IllegalStateException("Formato de arquivo inv�lido.");
		}
		Sudoku2 sudoku = new Sudoku2(Integer.parseInt(split[0]));
		
		for (int i = 0; i < sudoku.sizeSquare; i++) {
			inputLine = input.readLine();
			split = inputLine.split(" ");
			if (split.length != sudoku.sizeSquare) {
				input.close();
				throw new IllegalStateException("Formato de arquivo inv�lido.");
			}
			for (int j = 0; j < sudoku.sizeSquare; j++) {
				int val = Integer.parseInt(split[j]);
				if (val != 0) {
					sudoku.assign(Integer.parseInt(split[j]), i, j, true);
				}
			}
		}
		
		input.close();
		
		return sudoku;
	}
	
	public void assign(int value, int i, int j, boolean fixed) {
		int boxInitLineIndex = (i / size) * size;
		int boxInitColumIndex = (j / size) * size;
		
		for (int k = 0; k < sizeSquare; k++) {
			if (value-1 != k) {
				availableValuesMatrix[i][j][k] = false;				
			}
		}
		
		//percorre a linha e a coluna
		for (int k = 0; k < sizeSquare; k++) {
			availableValuesMatrix[i][k][value-1] = false;
			availableValuesMatrix[k][j][value-1] = false;
		}
		
		//percorre o box
		for (int k = 0; k < size; k++) 
			for (int l = 0; l < size; l++) 
				availableValuesMatrix[boxInitLineIndex+k][boxInitColumIndex+l][value-1] = false;
		
		matrix[i][j] = value;
		this.fixed[i][j] = fixed;
	}
	
	public void updateAvailableValues() {
		int conflicts = 0;
		int lastConflictIndex = -1;
		for (int i = 0; i < this.sizeSquare; i++) {
			for (int j = 0; j < this.sizeSquare; j++) {
				if (matrix[i][j] == 0) {
					for (int k = 0; k < this.sizeSquare; k++) 
						if (availableValuesMatrix[i][j][k]) {
							conflicts++;
							lastConflictIndex = k;
						}
					
					if (conflicts == 1)
						assign(lastConflictIndex+1, i, j, false);	
				}
			}
		}
	}
	
	public void swapLineValues(int i, int j, int k) {
		int tmpVal = matrix[i][j];
		matrix[i][j] = matrix[i][k];
		matrix[i][k] = tmpVal;
		
		
	}
	
	private void updateAvailableMatrixCell(int i, int j) {
		int boxInitLineIndex = (i / size) * size;
		int boxInitColumIndex = (j / size) * size;
		int value = matrix[i][j];
		
		for (int k = 0; k < sizeSquare; k++) { 
			availableValuesMatrix[i][j][k] = true;
		}
		
		//percorre a linha e a coluna
		for (int k = 0; k < sizeSquare; k++) {
			if (matrix[i][k] != 0) 
				availableValuesMatrix[i][k][matrix[i][k]-1] = false;
			if (matrix[k][j] != 0)
				availableValuesMatrix[k][j][matrix[k][j]-1] = false;
		}
		
		//percorre o box
		for (int k = 0; k < size; k++) {
			for (int l = 0; l < size; l++) {
				int valueIndex = matrix[boxInitLineIndex+k][boxInitColumIndex+l]-1;
				availableValuesMatrix[boxInitLineIndex+k][boxInitColumIndex+l][valueIndex] = false;				
			}
		}
	}
	
	public Sudoku2 clone() {
		Sudoku2 sudokuClone = new Sudoku2(this.size);
		
		for (int i = 0; i < this.sizeSquare; i++) {
			for (int j = 0; j < this.sizeSquare; j++) {
				sudokuClone.matrix[i][j] = this.matrix[i][j];
				sudokuClone.fixed[i][j] = this.fixed[i][j];				
			}			
		}

		
		for (int i = 0; i < this.sizeSquare; i++)
			for (int j = 0; j < this.sizeSquare; j++)
				for (int k = 0; k < this.sizeSquare; k++)				
					sudokuClone.availableValuesMatrix[i][j][k] = this.availableValuesMatrix[i][j][k];
		
		return sudokuClone;
	}
}
