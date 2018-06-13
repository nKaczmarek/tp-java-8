package lambda;

public class TestPredicate {

	public static void main(String[] args) {
		// java 7
		/*
		 * Predicate p = new Predicate(){
		 * 
		 * @Override public boolean test(String s) { return s.length()>10; }
		 * 
		 * };
		 */

		// java 8
		Predicate p = s -> s.length() > 10;

		// test command
		Command m = s -> System.out.println("test");

		// test générator
		Generator g = () -> "hello";

	}
}
