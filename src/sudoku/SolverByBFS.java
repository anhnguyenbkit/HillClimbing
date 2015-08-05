package sudoku;

import java.util.LinkedList;

public class SolverByBFS {

	public SudokuBFS searchMethod(SudokuBFS t){
		LinkedList<SudokuBFS> stiva = new LinkedList<SudokuBFS>();
		stiva.add(t);
		while(!stiva.isEmpty()){
			SudokuBFS s = stiva.getFirst();
			stiva.removeFirst();
			
			if(s.isFillFull()){
				System.out.println(t.toString());
				return s;
			}
			
			boolean gasit = false;
			
			for(int i=1; (i<=s.getSize()) && (!gasit); i++)
				for(int j=1; (j<=s.getSize()) && (!gasit); j++)
					if(s.getVal(i,j) == 0){
						gasit = true;
						for(int val=1; val<=s.getSize(); val++){
							SudokuBFS s_noua = new SudokuBFS(s);
							s_noua.complete(i,j,val);
							s_noua.toString();
							if( (s_noua.verify(i, j)) ){
								stiva.addLast(s_noua);
							}
						}
					}
		}
		return null;
	}
}
