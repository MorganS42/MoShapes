package main;

import java.util.ArrayList;
import java.util.List;

public class Nomenclature {
	public static String[] getSimplePolygonNamesFromSides(int sides) {
		if(sides < 3) return new String[0];
		List<String> names = new ArrayList<String>();
		
		if(sides <= 20) {
			// Primary name
			names.add(switch(sides) {
				case 3 	-> "Triangle";
				case 4 	-> "Quadrilateral";
				case 5 	-> "Penta";
				case 6 	-> "Hexa";
				case 7 	-> "Hepta";
				case 8 	-> "Octa";
				case 9 	-> "Ennea";
				case 10 -> "Deca";
				case 11 -> "Hendeca";
				case 12 -> "Dodeca";
				case 13 -> "Triskaideca";
				case 14 -> "Tetrakaideca";
				case 15 -> "Pentakaideca";
				case 16 -> "Hexakaideca";
				case 17 -> "Heptakaideca";
				case 18 -> "Octakaideca";
				case 19 -> "Enneakaideca";
				case 20 -> "Icosa";
			
				default -> "";
			});
			
			// Secondary Name
			names.add(switch(sides) {
				case 3 	-> "Tri";
				case 4 	-> "Tetra";
				
				case 7 	-> "Septa"; // Not preferred (Latin prefix)
				
				case 9 	-> "Nona"; // Technically wrong (Latin prefix), but is actually more common
				
				case 11 -> "Undeca"; // Not preferred (Roman prefix)
				
				case 13 -> "Trideca";
				case 14 -> "Tetradeca";
				case 15 -> "Pentadeca";
				case 16 -> "Hexadeca";
				case 17 -> "Heptadeca";
				case 18 -> "Octadeca";
				case 19 -> "Enneadeca";

				default -> "";
			});
		}
		else if(sides <= 99_999) {
			String asString = sides + "";
			
			int tenThousands = Integer.parseInt(asString.charAt(0) + "");
			int thousands = Integer.parseInt(asString.charAt(1) + "");
			int hundreds = Integer.parseInt(asString.charAt(2) + "");
			int tens = Integer.parseInt(asString.charAt(3) + "");
			int ones = Integer.parseInt(asString.charAt(4) + "");
			
			String name = "";
			
			if(tenThousands == 1) name += "myria";
			else if(tenThousands > 1) name += getSingleDigitGreekPrefix(hundreds) + "smyria";
			
			if(thousands == 1) name += "chillia";
			else if(hundreds > 1) name += getSingleDigitGreekPrefix(hundreds) + "schillia";
			
			if(hundreds == 1) name += "hecto";
			else if(hundreds > 1) name += getSingleDigitGreekPrefix(hundreds) + "hecta";
			
			if(tens > 2) name += (tens == 3 ? "tria" : getSingleDigitGreekPrefix(tens)) + "conta";
			else if(tens == 2) name += (ones == 0 ? "icosa" : "icosi");
			
			if(tens == 1) {
				for(String suffix : getSimplePolygonNamesFromSides(10 + ones)) {
					names.add(name + suffix.toLowerCase());
				}
			}
			else {
				String name1 = name + getSingleDigitGreekPrefix(ones);
				String name2 = name + "kai" + getSingleDigitGreekPrefix(ones);
				if(tens >= 2 && ones != 0) names.add(name2);
				names.add(name1);
			}
		}
		else {
			while() {
				
			}
		}
		
		while(names.remove(""));
		
		for(int i = 0; i < names.size(); i++) {
			names.set(i, capitalize(names.get(i) + "gon"));
		}
		names.add(sides + "-gon");
		
		return names.toArray(String[]::new);
	}
	
	public static String[] getDigitGreekPrefixes(int n) {
		if(n < 1) return new String[0];
		
		List<String> prefixes = new ArrayList<String>();
		
		if(n <= 20) {
			// Primary name
			prefixes.add(switch(n) {
				case 3 	-> "Tri";
				case 4 	-> "Tetra";
				case 5 	-> "Penta";
				case 6 	-> "Hexa";
				case 7 	-> "Hepta";
				case 8 	-> "Octa";
				case 9 	-> "Ennea";
				case 10 -> "Deca";
				case 11 -> "Hendeca";
				case 12 -> "Dodeca";
				case 13 -> "Triskaideca";
				case 14 -> "Tetrakaideca";
				case 15 -> "Pentakaideca";
				case 16 -> "Hexakaideca";
				case 17 -> "Heptakaideca";
				case 18 -> "Octakaideca";
				case 19 -> "Enneakaideca";
				case 20 -> "Icosa";
			
				default -> "";
			});
			
			// Secondary Name
			prefixes.add(switch(n) {
				case 7 	-> "Septa"; // Not preferred (Latin prefix)
				
				case 9 	-> "Nona"; // Technically wrong (Latin prefix), but is actually more common
				
				case 11 -> "Undeca"; // Not preferred (Roman prefix)
				
				case 13 -> "Trideca";
				case 14 -> "Tetradeca";
				case 15 -> "Pentadeca";
				case 16 -> "Hexadeca";
				case 17 -> "Heptadeca";
				case 18 -> "Octadeca";
				case 19 -> "Enneadeca";

				default -> "";
			});
		}
		
		String prefix = "";
		
		if(n >= 1_000_000) {
			int power = (int) Math.log10(n);
			power = power / 3 * 3;
			System.out.println(power);
		}
		else {
			String asString = n + "";
			
			int tenThousands = Integer.parseInt(asString.charAt(0) + "");
			int thousands = Integer.parseInt(asString.charAt(1) + "");
			int hundreds = Integer.parseInt(asString.charAt(2) + "");
			int tens = Integer.parseInt(asString.charAt(3) + "");
			int ones = Integer.parseInt(asString.charAt(4) + "");
			
			if(tenThousands == 1) prefix += "myria";
			else if(tenThousands > 1) prefix += getDigitGreekPrefixes(hundreds)[0] + "smyria";
			
			if(thousands == 1) prefix += "chillia";
			else if(hundreds > 1) prefix += getDigitGreekPrefixes(hundreds)[0] + "schillia";
			
			if(hundreds == 1) prefix += "hecto";
			else if(hundreds > 1) prefix += getDigitGreekPrefixes(hundreds)[0] + "hecta";
			
			if(tens > 2) prefix += (tens == 3 ? "tria" : getDigitGreekPrefixes(tens)[0]) + "conta";
			else if(tens == 2) prefix += (ones == 0 ? "icosa" : "icosi");
			
			if(tens == 1) prefix += getDigitGreekPrefixes(10 + ones);
			else prefix += "kai" + getDigitGreekPrefixes(ones)[0];
		}
		
		return prefixes;
	}
	
	public static String getGreekPrefixFromPower(int n) {
		if(n % 3 != 0) return ""; // Not multiple of three
		
		return switch(n) {
			case 3 	-> "chillia";
			case 6 	-> "mega";
			case 9 	-> "giga";
			case 12 -> "tera";
			case 15 -> "peta";
			case 18 -> "exa";
			case 21 -> "zetta";
			case 24 -> "yotta";
			case 27 -> "ronna";
			case 30 -> "quetta";
			
			default -> "";
		};
	}
	
	public static int getSidesFromSimplePolygonName(String name) {
		// TODO Implement dictionary approach
		
		return 0;
	}
	
	public static String capitalize(String str) {
		if(str == null || str.length() <= 1) return str;
	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}
