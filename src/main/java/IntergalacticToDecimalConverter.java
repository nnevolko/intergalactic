package main.java;

public class IntergalacticToDecimalConverter implements Converter {

	private Dictionary dictionary;

	public IntergalacticToDecimalConverter(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.Converter#convert(java.lang.String)
	 */

	public int convert(String intergallacticAmount) {

		RomanToDecimalConverter roman2DecimalConverter = new RomanToDecimalConverter();
		IntergalacticToRomanConverter i2RomanConverter = new IntergalacticToRomanConverter(
				dictionary);

		String amountInRoman = i2RomanConverter.convert(intergallacticAmount);
		int amountInDecimal = roman2DecimalConverter.convert(amountInRoman);
		// System.out.println(intergallacticAmount + " -> " + amountInRoman + " is " +
		// amountInDecimal);

		return amountInDecimal;

	}
}
