package test;

import static org.junit.Assert.assertTrue;
import main.java.RomanToDecimalConverter;

import org.junit.Test;

public class RomanToDecimalConverterTest {

	@Test
	public void convertTest() {
		RomanToDecimalConverter newConverter = new RomanToDecimalConverter();
		int result = newConverter.convert("XXII");
		assertTrue(result == 22);

		int result2 = newConverter.convert("XLVIII");
		assertTrue(result2 == 48);

	}

}
