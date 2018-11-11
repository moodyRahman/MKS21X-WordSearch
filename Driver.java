import java.util.*;

public class Driver{
	public static void main(String[] args) {
		WordSearch a = new WordSearch(15, 15, "words.txt", 500);
		System.out.println(a);

		// Random r = new Random();
		// for (int x = 0; x < 100; x++){
		// 	System.out.println(r.nextInt() % 2 );
		// }
		// WordSearch b = new WordSearch(10, 10, "words.txt");
		// a.editor(0, 0, 'm');
		// a.editor(9, 9, 'a');
		//
		// a.editor(0, 9, 'd');
		// System.out.println(a);
		//
		// System.out.println(a.addWordHorizontalBackwards("jpd", 0, 7));
		// System.out.println(a.addWordHorizontalBackwards("cat", 1, 0));
		// System.out.println(a);
		// System.out.println();
		// System.out.println();
		//
		// System.out.println();
		// System.out.println();
		//
		// System.out.println(b);
		//
		// System.out.println();
		// System.out.println();
		// System.out.println();
		//
		// WordSearch d = new WordSearch(10, 10, "words.txt");
		// System.out.println(d);
		// d.addWord("osbfucate", 8, 0, 0, 1);
		// d.addWord("catos", 4, 5, 1, -1);
		// System.out.println(d);



		// System.out.println(b.addWord("obs", 5, 5, 1, 1));
		// System.out.println(b.wordchecker("caa", 5, 4, 1, 1));
		// System.out.println(b.wordchecker("caa", 5, 4, 1, -1));
		// System.out.println(b.wordchecker("caa", 5, 4, -1, 1));
		// System.out.println(b.wordchecker("caa", 5, 4, -1, -1));
		// System.out.println();
		// System.out.println(b.wordchecker("caa", 5, 4, 1, 0));
		// System.out.println(b.wordchecker("caa", 5, 4, 0, 1));
		// System.out.println(b.wordchecker("caa", 5, 4, -1, 0));
		// System.out.println(b.wordchecker("caa", 5, 4, 0, -1));
		/**
		System.out.println("");
		System.out.println(a.addWordVertical("neko", 6, 0));
		System.out.println(a);
		System.out.println("");
		System.out.println("");
		System.out.println(b);
		System.out.println(b.addWordDiagonal("b00o", 0, 0));
		System.out.println(b);
		System.out.println("");
		System.out.println("");
		System.out.println("-------------------------");

		WordSearch c = new WordSearch(6, 7);
		System.out.println(c);
		c.addWordDiagonal("ab", 4, 0);
		c.addWordDiagonal("abc", 3, 0);
		c.addWordDiagonal("abcd", 2, 0);
		c.addWordDiagonal("ab", 0, 5);
//		c.addWordHorizontal("abcabcb", 0, 0);
		System.out.println(c);
		**/
	}
}
