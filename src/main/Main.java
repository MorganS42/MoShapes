package main;

public class Main {
	public static void main(String... args) {
		String[] names = Nomenclature.getSimplePolygonNamesFromSides(999);
		System.out.println();
		System.out.println("Primary name: ");
		System.out.println(names[0]);
		System.out.println();
		System.out.println("Other names: ");
		for(int i = 1; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}
}
