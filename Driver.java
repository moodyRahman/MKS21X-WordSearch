public class Driver{
	public static void main(String[] args) {
		WordSearch a = new WordSearch(10,10);
		WordSearch b = new WordSearch(4, 5);
		a.editor(0, 0, 'm');
		a.editor(9, 9, 'a');

		a.editor(0, 9, 'd');
		System.out.println(a);

		System.out.println(a.addWordHorizontal("jpdaaaaaa", 5, 1));
		System.out.println(a);
		System.out.println("");
		System.out.println(a.addWordVertical("neko", 6, 0));
		System.out.println(a);
	}
}
