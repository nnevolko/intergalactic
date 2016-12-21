package main.java;

public class RomanToDecimalConverter implements Converter {

	public int convert(String number) {
		if (number == null || number.isEmpty())
			return 0;
		if (number.startsWith("M"))
			return 1000 + convert(number.substring(1));
		if (number.startsWith("CM"))
			return 900 + convert(number.substring(2));
		if (number.startsWith("D"))
			return 500 + convert(number.substring(1));
		if (number.startsWith("CD"))
			return 400 + convert(number.substring(2));
		if (number.startsWith("C"))
			return 100 + convert(number.substring(1));
		if (number.startsWith("XC"))
			return 90 + convert(number.substring(2));
		if (number.startsWith("L"))
			return 50 + convert(number.substring(1));
		if (number.startsWith("XL"))
			return 40 + convert(number.substring(2));
		if (number.startsWith("X"))
			return 10 + convert(number.substring(1));
		if (number.startsWith("IX"))
			return 9 + convert(number.substring(2));
		if (number.startsWith("V"))
			return 5 + convert(number.substring(1));
		if (number.startsWith("IV"))
			return 4 + convert(number.substring(2));
		if (number.startsWith("I"))
			return 1 + convert(number.substring(1));

		throw new ArrayIndexOutOfBoundsException("Problem parcing the number:" + number);
	}

}
