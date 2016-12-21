package main.java;

import java.util.Arrays;
import java.util.List;

public class IntergalacticToRomanConverter {

	private Dictionary dictionary;

	public IntergalacticToRomanConverter(Dictionary dictionary) {
		// dictionary cannot be null
		this.dictionary = dictionary;
	}

	public String convert(String amount) {

		String result = "";
		List<String> quantity = Arrays.asList(amount.split("\\s+"));

		for (String word : quantity) {
			if (dictionary.containsWord(word.trim())) {
				result += dictionary.getTranslation(word.trim());
			} else {
				result = "";
				throw new ArrayIndexOutOfBoundsException("Problem parcing following amount:"
						+ amount);
			}
		}

		return result;
	}

}
