package sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SudokuDFS {
	private int size;
	private int[][] problem;
	
	public SudokuDFS(String fileNameInput) {
		initialState(fileNameInput);
	}
	
	private void initialState(String fis) {
		try{
			BufferedReader br = new BufferedReader(new FileReader(fis));
			size = Integer.parseInt(br.readLine());
			size *= size; //Because input sudoku in file if 9 then n =3
			problem = new int[size][size];
			for(int i=0; i<size; i++){
				String[] s = br.readLine().split(" ");
				for(int j=0; j<s.length; j++){
					problem[i][j] = Integer.parseInt(s[j]);
				}
			}
			br.close();
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public String toString(){
		String s = new String();
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				s += problem[i][j] + " ";
			}
			s += System.getProperty("line.separator");
		}
		return s;
	}
	
	public int getSize() {
		return size;
	}
	
	public int[][] getMatrix() {
		return problem;
	}
}
