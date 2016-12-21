package test;

import static org.junit.Assert.assertTrue;
import main.java.Converter;
import main.java.Dictionary;
import main.java.IntergalacticToDecimalConverter;

import org.junit.Before;
import org.junit.Test;

public class IntergalacticToDecimalConverterTest {

	Converter newConverter;

	@Before
	public void setUp() {
		Dictionary dictionary = new Dictionary();

		dictionary.addNewWord("glob", "I");
		dictionary.addNewWord("prok", "V");
		dictionary.addNewWord("pish", "X");
		dictionary.addNewWord("tegj", "L");
		newConverter = new IntergalacticToDecimalConverter(dictionary);
	}

	@Test
	public void convertTest() {
		int result = newConverter.convert("pish glob pish");
		assertTrue(result == 19);
	}

}
