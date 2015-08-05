package sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Sudoku {
	
	public int size;
	public int sizeSquare;
	public int[][] matrix;
	public boolean[][] fixed;
	
	public Sudoku(int n) {
		this.size = n;
		this.sizeSquare = n*n;
		this.matrix = new int[sizeSquare][sizeSquare];
		this.fixed = new boolean[sizeSquare][sizeSquare];
	}
	
	public static Sudoku readSudoku(String fileName) throws IOException {
		FileReader fileReader = new FileReader(fileName);
		BufferedReader input = new BufferedReader(fileReader);
		
		String inputLine = input.readLine();
		String[] split = inputLine.split(" ");
		if (split.length != 1) {
			input.close();
			throw new IllegalStateException("Formato de arquivo inv�lido.");
		}
		Sudoku sudoku = new Sudoku(Integer.parseInt(split[0]));
		
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
					sudoku.matrix[i][j] = Integer.parseInt(split[j]);
					sudoku.fixed[i][j] = true;
				}
			}
		}
		
		input.close();
		
		return sudoku;
	}
	
	public Sudoku clone() {
		Sudoku sudokuClone = new Sudoku(this.size);
		
		for (int i = 0; i < this.sizeSquare; i++) {
			for (int j = 0; j < this.sizeSquare; j++) {
				sudokuClone.matrix[i][j] = this.matrix[i][j];
				sudokuClone.fixed[i][j] = this.fixed[i][j];
			}
		}
		
		return sudokuClone;
	}
	
	public String toString() {
		String str = "";
		
		for (int i = 0; i < this.sizeSquare; i++) {
			str += this.matrix[i][0];
			for (int j = 1; j < this.sizeSquare; j++) {
				str += " " + this.matrix[i][j];
			}
			str += "\n";
		}
		
		return str;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(matrix);
		result = prime * result + size;
		result = prime * result + sizeSquare;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sudoku other = (Sudoku) obj;
		if (!Arrays.deepEquals(matrix, other.matrix))
			return false;
		if (size != other.size)
			return false;
		if (sizeSquare != other.sizeSquare)
			return false;
		return true;
	}	

}