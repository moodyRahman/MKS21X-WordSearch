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
        Random seedgen = new Random();
        seed = seedgen.nextInt();
        randgen = new Random(seed);
    }

    public WordSearch (int rows,int cols, String filename, int seed){
		consruct(rows, cols, filename);
        this.seed = seed;
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
            output += "|";
			for (int x = 0; x < data.length; x++){
			output += data[x][y];
			}
            output += "|";
			output += "\n";
		}
        output += "your seed: ";
        output += seed;
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
            }
        }
    }

}
