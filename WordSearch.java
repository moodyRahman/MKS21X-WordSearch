import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class WordSearch{
    private char[][]data;
    private int seed;
    private Random randgen;
	private int rowLength;
	private int coLength;
    private ArrayList<String>wordsToAdd = new ArrayList<String>();
    private ArrayList<String>wordsAdded;


    public WordSearch (int rows,int cols, String filename){
		consruct(rows, cols, filename);
        randgen = new Random();
    }

    public WordSearch (int rows,int cols, String filename, int seed){
		consruct(rows, cols, filename);
        randgen = new Random(seed);
    }

    private void consruct(int rows,int cols, String filename){
        data = new char[cols][rows];
		for (int x = 0; x < data.length; x++){
			for (int y = 0; y < data[x].length; y++){
				data[x][y] = '_';
			}
		}
		rowLength = rows;
		coLength = cols;

        try{
            reader(filename);
        } catch (FileNotFoundException e){
            System.out.println("File not found: " + filename);
            System.exit(1);
        }
    }

    public void reader(String fileName) throws FileNotFoundException{
        File f = new File(fileName);//can combine
        Scanner in = new Scanner(f);//into one line
        while (in.hasNextLine()) {
            String a = in.nextLine();
            wordsToAdd.add(a);
        }
        in.close();
    }


    private void clear(){
		for (int x = 0; x < data.length; x++){
			for (int y = 0; y < data[x].length; y++){
				this.data[x][y] = '_';
			}
		}
    }


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



// ──────────────────────────────
// ┌─┐┬─┐┌─┐┌┬┐┌─┐┌┬┐┬ ┬┌─┐┌─┐┌─┐
// ├─┘├┬┘│ │ │ │ │ │ └┬┘├─┘├┤ └─┐
// ┴  ┴└─└─┘ ┴ └─┘ ┴  ┴ ┴  └─┘└─┘
// ┌─┐┌┬┐┌┬┐  ┬ ┬┌─┐┬─┐┌┬┐┌─┐
// ├─┤ ││ ││  ││││ │├┬┘ ││└─┐
// ┴ ┴─┴┘─┴┘  └┴┘└─┘┴└──┴┘└─┘
// ──────────────────────────────

     public boolean addWordHorizontal(String word,int row, int col){
         if (!horizCheck(word, row, col)){
             return false;
         }
 		int temp = 0;
 		for (int x = col; x < word.length() + col; x++){
 			data[x][row] = word.charAt(temp);
 			temp++;
 		}
        return true;
     }

     private static String reverseString(String str){
         if(str.isEmpty()){
             return str;
         }
         else{
             return reverseString(str.substring(1))+str.charAt(0);
         }
     }

     public boolean addWordHorizontalBackwards(String word,int row, int col){
        if (!horizCheck(word, row, col)){
            return false;
        }
 		int temp = 0;
        word = reverseString(word);
 		for (int x = col; x < word.length() + col; x++){
 			data[x][row] = word.charAt(temp);
 			temp++;
 		}
        return true;
     }


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


    public boolean addWordDiagonal(String word, int row, int col){
        if (col + word.length() > coLength || col < 0){
			return false;
		}
		if (row + word.length() > rowLength || row < 0){
			return false;
		}
        int temp = 0;
        int coltemp = col;
        for (int y = row; y < word.length() + row; y++){
			data[coltemp][y] = word.charAt(temp);
			temp++;
            coltemp++;
		}
        return true;
    }


    private boolean diagCheck(String word, int row, int col){
        if (col + word.length() > coLength || col < 0){
			return false;
		}
		if (row + word.length() > rowLength || row < 0){
			return false;
		}
        return true;
    }

    private boolean vertCheck(String word, int row, int col){
        if (col >= coLength - 1 || col < 0){
			return false;
		}
		if (row + word.length() > rowLength || row < 0){
			return false;
		}
        return true;
    }

    private boolean horizCheck(String word, int row, int col){
        if (col + word.length() > coLength || col < 0){
 			return false;
 		}
 		if (row >= rowLength - 1 || row < 0){
 			return false;
 		}
        return true;
    }


    private boolean checker(String word, int col, int row, int rowIncrement, int colIncrement){
        if (rowIncrement == 0 && colIncrement == 0){
            return false;
        }
        if (rowIncrement == 0){
            if (!vertCheck(word, row, col)){
                return false;
            }
        }
        if (colIncrement == 0){
            if (!horizCheck(word, row, col)){
                return false;
            }
        }
        return true;
    }

    // ──────────────────────────────
    // ┌─┐┬┌┐┌┌─┐┬
    // ├┤ ││││├─┤│
    // └  ┴┘└┘┴ ┴┴─┘
    // ┌─┐┌┬┐┌┬┐  ┬ ┬┌─┐┬─┐┌┬┐┌─┐
    // ├─┤ ││ ││  ││││ │├┬┘ ││└─┐
    // ┴ ┴─┴┘─┴┘  └┴┘└─┘┴└──┴┘└─┘
    // ──────────────────────────────

    private boolean wordchecker(String word,int row, int col, int rowIncrement, int colIncrement){
        if (rowIncrement == 0 && colIncrement == 0){
            return false;
        }
        try {
            int x = col;
            int y = row;
            for (int i = 0; i < word.length(); i++){
                char letter = word.charAt(i);
                if (data[x][y] != letter && data[x][y] != '_'){
                    return false;
                }
                x += colIncrement;
                y += rowIncrement;
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
    }


    public boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){
        if (!wordchecker(word, row, col, rowIncrement, colIncrement)){
            return false;
        }
        int x = col;
        int y = row;
        for (int i = 0; i < word.length(); i++){
            char letter = word.charAt(i);
            data[x][y] = letter;
            x += colIncrement;
            y += rowIncrement;
        }
        return true;
    }
    // ┬─┐┌─┐┌┐┌┌┬┐┌─┐┌┬┐  ┌─┐┌┬┐┌┬┐┌─┐┬─┐┌─┐
    // ├┬┘├─┤│││ │││ ││││  ├─┤ ││ ││├┤ ├┬┘└─┐
    // ┴└─┴ ┴┘└┘─┴┘└─┘┴ ┴  ┴ ┴─┴┘─┴┘└─┘┴└─└─┘

    public void addAll(){
        int xcoor;
        int ycoor;
        int rinc;
        int cinc;
        int added = 0;
        for (int x = 0; x < wordsToAdd.size(); x++){
            String currword = wordsToAdd.get(x);
            boolean isAdded = false;
            int count = 100;
            while (isAdded == false && count > 0){
                xcoor = Math.abs(randgen.nextInt() % rowLength);
                ycoor = Math.abs(randgen.nextInt() % coLength);
                rinc = (randgen.nextInt() % 2);
                cinc = (randgen.nextInt() % 2);
                if (isAdded = addWord(currword, ycoor, xcoor, rinc, cinc)){
                    added++;
                }
                count--;
                System.out.println(added);
            }
        }
    }

}
