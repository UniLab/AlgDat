package backtracking;

public class Test {
	public static void main(String[]args) {
		boolean[][] m = {{true, false, true, false, false, false},
				{false, false, true, false, true, false},
				{false, true, false, false, true, false},
				{false, false, false, true, false, false},
				{false, false, true, false, false, true},
				{false, true, false, false, true, false},
				{false, false, false, false, false, true}
				};
		StudentiEsami s = new StudentiEsami(m);
		s.risolvi();
		System.out.println(java.util.Arrays.toString(s.sol));
	}
}
