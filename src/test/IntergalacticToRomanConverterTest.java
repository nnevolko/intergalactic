package test;

import static org.junit.Assert.assertTrue;
import main.java.Dictionary;
import main.java.IntergalacticToRomanConverter;

import org.junit.Before;
import org.junit.Test;

public class IntergalacticToRomanConverterTest {

	IntergalacticToRomanConverter newConverter;

	@Before
	public void setUp() {
		Dictionary dictionary = new Dictionary();

		dictionary.addNewWord("glob", "I");
		dictionary.addNewWord("prok", "V");
		dictionary.addNewWord("pish", "X");
		dictionary.addNewWord("tegj", "L");
		newConverter = new IntergalacticToRomanConverter(dictionary);
	}

	@Test
	public void convertTest() {
		String result = newConverter.convert("pish glob pish");
		assertTrue(result.equals("XIX"));
	}

}
