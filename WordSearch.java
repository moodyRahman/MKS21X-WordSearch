public class WordSearch{
    private char[][]data;
	private int rowLength;
	private int coLength;

    /**Initialize the grid to the size specified

     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows,int cols){
		data = new char[cols][rows];
		for (int x = 0; x < data.length; x++){
			for (int y = 0; y < data[x].length; y++){
				data[x][y] = '_';
			}
		}
		rowLength = rows;
		coLength = cols;
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
		for (int x = 0; x < data.length; x++){
			for (int y = 0; y < data[x].length; y++){
				this.data[x][y] = '_';
			}
		}
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
	public String toString(){
		String output = "";
		for (int y = rowLength - 1; y >= 0; y--){
			for (int x = 0; x < data.length; x++){
			output += data[x][y];
			}
			output += "\n";
		}
		return output;
	}

	public void editor(int x, int y, char inp){
		this.data[x][y] = inp;
	}

	public void altPrint(){
		for (int x = 0; x < data.length; x++){
			for (int y = 0; y < data[x].length; y++){
				System.out.print(data[x][y] + ", ");
			}
			System.out.print("\n");
		}
	}


    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned

     * and the board is NOT modified.

     */
    public boolean addWordHorizontal(String word,int row, int col){
		if (col + word.length() > coLength || col < 0){
			return false;
		}
		if (row >= rowLength - 1 || row < 0){
			return false;
		}
		int temp = 0;
		for (int x = col; x < word.length() + col; x++){
			data[x][row] = word.charAt(temp);
			temp++;
		}
		return true;
    }


   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.

     */
    public boolean addWordVertical(String word,int row, int col){
		if (col >= coLength - 1 || col < 0){
			return false;
		}
		if (row + word.length() > rowLength || row < 0){
			return false;
		}
		int temp = 0;
		for (int y = row; y < word.length() + row; y++){
			data[col][y] = word.charAt(temp);
			temp++;
		}
		return true;
    }

}
