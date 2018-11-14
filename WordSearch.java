import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class WordSearch {
private char[][] data;
private int seed;
private Random randgen;
private int rowLength;
private int coLength;
private ArrayList<String>wordsToAdd = new ArrayList<String>();
private ArrayList<String>wordsAdded = new ArrayList<String>();
private boolean printKey = false;


public WordSearch (int rows,int cols, String filename, String key){
        consruct(rows, cols, filename);
        Random seedgen = new Random();
        seed = seedgen.nextInt();
        randgen = new Random(seed);
        if (key.equals("key")) {
                printKey = true;
        }
}

public WordSearch (int rows,int cols, String filename, int seed, String key){
        consruct(rows, cols, filename);
        this.seed = seed;
        randgen = new Random(seed);
        if (key.equals("key")) {
                printKey = true;
        }
}

private void consruct(int rows,int cols, String filename){
        data = new char[cols][rows];
        for (int x = 0; x < data.length; x++) {
                for (int y = 0; y < data[x].length; y++) {
                        data[x][y] = '_';
                }
        }
        rowLength = rows;
        coLength = cols;

        try{
                reader(filename);
        } catch (FileNotFoundException e) {
                System.out.println("File not found: " + filename);
                System.exit(1);
        }
}

private void reader(String fileName) throws FileNotFoundException {
        File f = new File(fileName);//can combine
        Scanner in = new Scanner(f);//into one line
        while (in.hasNextLine()) {
                String a = in.nextLine();
                wordsToAdd.add(a);
        }
        in.close();
}


private void clear(){
        for (int x = 0; x < data.length; x++) {
                for (int y = 0; y < data[x].length; y++) {
                        this.data[x][y] = '_';
                }
        }
}


private String bankGen(){
        String output = "words: ";
        for (int x = 0; x < wordsAdded.size(); x++) {
                output += wordsAdded.get(x) + ", ";
        }
        return output;
}

private String stringGen(){
        addAll();
        String output = "";
        for (int y = rowLength - 1; y >= 0; y--) {
                output += "| ";
                for (int x = 0; x < data.length; x++) {
                        output += data[x][y] + " ";
                }
                output += "|";
                output += "\n";
        }
        output += "your seed: ";
        output += seed + "\n";
        output += bankGen();
        return output;
}

private char randLetter(){
        String init = "abcdefghijklmnopqrstuvwxyz";
        int pos = randgen.nextInt();
        pos = pos % 13;
        pos = pos + 13;
        return init.charAt(pos);
}

public String toString(){
        if (printKey) {
                return stringGen();
        }
        else {
                String curr = stringGen();
                String output = "";
                for (int x = 0; x < curr.length(); x++) {
                        if (curr.charAt(x) == '_') {
                                output += randLetter();
                        }
                        else {
                                output += curr.charAt(x);
                        }
                }
                return output;
        }
}

public void editor(int x, int y, char inp){
        this.data[x][y] = inp;
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
        if (rowIncrement == 0 && colIncrement == 0) {
                return false;
        }
        try {
                int x = col;
                int y = row;
                for (int i = 0; i < word.length(); i++) {
                        char letter = word.charAt(i);
                        if (data[x][y] != letter && data[x][y] != '_') {
                                return false;
                        }
                        x += colIncrement;
                        y += rowIncrement;
                }
                return true;
        } catch (ArrayIndexOutOfBoundsException e) {
                return false;
        }
}


private boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){
        if (!wordchecker(word, row, col, rowIncrement, colIncrement)) {
                return false;
        }
        int x = col;
        int y = row;
        for (int i = 0; i < word.length(); i++) {
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

private void addAll(){
        int xcoor;
        int ycoor;
        int rinc;
        int cinc;
        int added = 0;
        Collections.shuffle(wordsToAdd, randgen);
        for (int x = 0; x < wordsToAdd.size(); x++) {
                rinc = (randgen.nextInt() % 2);
                cinc = (randgen.nextInt() % 2);
                while ((rinc == 0) && (cinc == 0)) {
                        rinc = (randgen.nextInt() % 2);
                        cinc = (randgen.nextInt() % 2);
                }
                String currword = wordsToAdd.get(x);
                boolean isAdded = false;
                int count = 100;
                while (isAdded == false && count > 0) {
                        xcoor = Math.abs(randgen.nextInt() % rowLength);
                        ycoor = Math.abs(randgen.nextInt() % coLength);
                        if (isAdded = addWord(currword, ycoor, xcoor, rinc, cinc)) {
                                added++;
                                wordsAdded.add(currword);
                        }
                        count--;
                }
        }
}

public static void main(String[] args) {
        try {
                if (args.length == 3) {
                        int rows = Integer.parseInt(args[0]);
                        int cols = Integer.parseInt(args[1]);
                        WordSearch out = new WordSearch(rows, cols, args[2], "false");
                        System.out.println(out);
                        System.exit(1);
                }
                if (args.length == 4) {
                        int rows = Integer.parseInt(args[0]);
                        int cols = Integer.parseInt(args[1]);
                        int seed = Integer.parseInt(args[3]);
                        WordSearch out = new WordSearch(rows, cols, args[2], seed, "false");
                        System.out.println(out);
                        System.exit(1);
                }
                if (args.length == 5) {
                        int rows = Integer.parseInt(args[0]);
                        int cols = Integer.parseInt(args[1]);
                        int seed = Integer.parseInt(args[3]);
                        WordSearch out = new WordSearch(rows, cols, args[2], seed, args[4]);
                        System.out.println(out);
                        System.exit(1);
                }
        } catch (NumberFormatException e) {
                System.out.println("run program as \"java WordSearch rowlength colheight wordfile [OPTIONAL] seed ");
                System.exit(1);
        }
        System.out.println("run program as \"java WordSearch <int row length> <int column length> <String filename> [OPTIONAL] <int seed> ");
}

}
