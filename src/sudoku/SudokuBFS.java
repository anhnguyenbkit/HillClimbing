package sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SudokuBFS {
	private int n;
	private int nr_filled; //number cell filled
	private int[][] a;	//maxtrix save tmp
	
	public SudokuBFS(int n) {
		this.n = n;
		a = new int[n+1][n+1];
		nr_filled = 0;
	}
	
	public SudokuBFS(String fis) {
		initialState(fis);
	}
	
	public SudokuBFS(SudokuBFS s) {
		this(s.n);
		for(int i=1; i<=n; i++)
			for(int j=1; j<=n; j++)
				a[i][j] = s.a[i][j];
		nr_filled = s.nr_filled;
	}
	
	public boolean getStatus(int i, int j) {
		if (a[i][j]!=0)
			return false;
		return true;
	}
	
	public void setNR() {
		nr_filled--;
	}
	
	private void initialState(String fis) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fis));
			n = Integer.parseInt(br.readLine());
			n *=n; //Because intput sudoku n*n,4 = 2*2
			a = new int[n+1][n+1]; 
			nr_filled = 0;
			for(int i=1; i<=n; i++){
				String[] s = br.readLine().split(" ");
				for(int j=0; j<s.length; j++)
				{
					a[i][j+1] = Integer.parseInt(s[j]);
					if(a[i][j+1] != 0)
						nr_filled++;
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
	
	public boolean isFillFull(){
		if(nr_filled == n*n) 
			return true;
		return false;
	}
	
	public boolean verify(int l, int c) {
		
		for(int i=1; i<=n; i++)
			if((i!= l) && (a[i][c] == a[l][c]))
				return false;
		for(int j=1; j<n; j++)
			if((j!=c) && (a[l][j] == a[l][c]))
				return false;
		int rad = (int)Math.sqrt(n);
		int l_start = l / rad;
		int c_start = c / rad;
		
		if(l % rad == 0) l_start -= 1;
		if(c % rad == 0) c_start -= 1;
		
		l_start = l_start * rad + 1;
		c_start = c_start * rad + 1;
		
		int l_end = l_start + rad - 1;
		int c_end = c_start + rad - 1;
		
		for(int i= l_start; i<= l_end; i++)
			for(int j=c_start; j<=c_end; j++)
				if((i!=l) && (j!=c) && (a[i][j]==a[l][c]))
					return false;
		return true;
	}
	
	public void complete(int l, int c, int val){
		if((1<=l) && (l<=n) && (1<=c) && (c<=n)) {
			if(a[l][c] == 0)
				nr_filled++;
			a[l][c] = val;
		}
	}
	
	public int getVal(int l, int c) {
		return a[l][c];
	}
	
	public int getSize() {
		return n;
	}
	
	public String toString(){
		String s = new String();
		for(int i=1; i<=n; i++){
			for(int j=1; j<=n; j++){
				s += a[i][j] + " ";
			}
			s += System.getProperty("line.separator");
		}
		return s;
	}
}
