package main.java;

import java.util.Map;
import java.util.TreeMap;

public class Dictionary {

	private Map<String, String> newLanguageToRoman = new TreeMap<String, String>();

	public void addNewWord(String newLanguage, String roman) {
		newLanguageToRoman.put(newLanguage, roman);
	}

	public Map<String, String> getDictionary() {
		return newLanguageToRoman;
	}

	public boolean containsWord(String word) {
		return newLanguageToRoman.containsKey(word) ? true : false;
	}

	public String getTranslation(String word) {
		return newLanguageToRoman.get(word);
	}

	@Override
	public String toString() {
		StringBuffer sf = new StringBuffer();
		sf.append("Dictionary {");
		for (Map.Entry<String, String> entry : newLanguageToRoman.entrySet()) {
			sf.append("\n\t" + entry.getKey() + ":" + entry.getValue());
		}
		sf.append("\n}");
		return sf.toString();
	}

}
