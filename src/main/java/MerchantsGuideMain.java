package main.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MerchantsGuideMain {

	final static String CREDITS = "Credits";
	final static String HOW_MUCH = "How much";
	final static String HOW_MANY = "How many";

	final static Boolean DEBUG = false;

	public static void main(String[] args) {
		// read train routes
		List<String> intergalacticInputLines = readInput("src/resources/intergalactic_lan1.txt");
		List<String> merchandisePossible = readInput("src/resources/metals.txt");
		if (DEBUG) {
			System.out.println(intergalacticInputLines.toString());
		}

		Map<Integer, Boolean> successMap = new HashMap<Integer, Boolean>();

		if (DEBUG) {
			System.out.println("------------1------------");
		}
		Dictionary dictionary = populateDictionary(intergalacticInputLines, successMap);
		if (DEBUG) {
			System.out.println(dictionary);
		}

		// System.out.println("------------2------------");
		PriceList priceList = addMerchandise(intergalacticInputLines, merchandisePossible,
				successMap);
		if (DEBUG) {
			System.out.println(priceList);
		}

		if (DEBUG) {
			System.out.println("-----------3-------------");
		}
		Pattern p3 = Pattern.compile("(?i)^" + HOW_MUCH + "(\\sis\\s){1}(\\D+)(\\s\\\\?){1}");

		List<String> howMuchIsList = new ArrayList<String>();
		int lineCounter3 = 0;
		for (String inputLine : intergalacticInputLines) {
			Matcher matcher = p3.matcher(inputLine);
			while (matcher.find()) {
				String amount = matcher.group(2);
				howMuchIsList.add(amount);
				// to keep track of processed lines
				successMap.put(lineCounter3, true);
			}
			lineCounter3++;
		}

		Converter newConverter = new IntergalacticToDecimalConverter(dictionary);

		for (String amount : howMuchIsList) {
			int amountInCredits = newConverter.convert(amount);
			System.out.println(amount + " -> " + amountInCredits + " Credits");
		}

		if (DEBUG) {
			System.out.println("-----------4-------------");
		}
		// how many Credits is glob prok Silver ?
		Pattern p4 = Pattern.compile("(?i)^" + HOW_MANY + "(\\s" + CREDITS
				+ "\\sis\\s){1}(\\D+)\\s(\\w+)(\\s\\\\?){1}");

		int lineCounter = 0;
		for (String inputLine : intergalacticInputLines) {
			Matcher matcher = p4.matcher(inputLine);
			while (matcher.find()) {
				String amount = matcher.group(2);
				String merchandise = matcher.group(3);
				if (DEBUG) {
					System.out.println(inputLine + " [" + amount + "," + merchandise + "]");
				}
				int quantityNeeded = newConverter.convert(amount);
				QuantityPrice qp = priceList.getMerchandiseCost(merchandise);
				if (DEBUG) {
					System.out.println(qp);
				}
				if (qp != null && qp.getQuantity() != null) {
					int quantityPriced = newConverter.convert(qp.getQuantity());

					int priceInCredits = qp.getPrice() * quantityNeeded / quantityPriced;
					System.out.println(amount + " " + merchandise + " " + priceInCredits
							+ " Credits");
				}
				// to keep track of processed lines
				successMap.put(lineCounter, true);

			}
			lineCounter++;
		}

		// Check unprocessed lines
		for (int i = 0; i < intergalacticInputLines.size(); i++) {
			if (!successMap.containsKey(i)) {
				if (DEBUG) {
					System.out.println(intergalacticInputLines.get(i));
				}
				System.out.println("I have no idea what you are talking about");
			}
		}

	}

	public static Dictionary populateDictionary(List<String> intergalacticInputLines,
			Map<Integer, Boolean> successMap) {
		// prok is V
		// tegj is L
		List<String> workAroundLines = new ArrayList<String>();
		Pattern p0 = Pattern.compile("[^\\\\?]$"); // remove lines that end with ?
		int lineIndex = 0;
		for (String inputLine : intergalacticInputLines) {
			Matcher matcher = p0.matcher(inputLine);
			while (matcher.find()) {
				workAroundLines.add(inputLine);

				// keep track of lines that were successfully processed
				successMap.put(lineIndex, true);
			}
			lineIndex++;
		}

		Dictionary dictionary = new Dictionary();

		Pattern p1 = Pattern.compile("(\\w+)(\\sis\\s){1}(\\D){1}");
		for (String inputLine : workAroundLines) {
			Matcher matcher = p1.matcher(inputLine);
			while (matcher.find()) {
				String newCurrency = matcher.group(1);
				String romanEquivalent = matcher.group(3);
				//System.out.println(inputLine + "[" + newCurrency + " ," + romanEquivalent + "]");
				dictionary.addNewWord(newCurrency, romanEquivalent);
			}
		}

		return dictionary;
	}

	public static PriceList addMerchandise(List<String> intergalacticInputLines,
			List<String> metalsAndDirt, Map<Integer, Boolean> successMap) {

		// Exchange Rate
		// glob glob Silver is 34 Credits

		// Pattern p2 = Pattern.compile("(.+?)(\\sis\\s){1}(\\d+)(\\s" + CREDITS + "){1}");
		Pattern p2 = Pattern.compile("(\\D+)\\s(\\w+)(\\sis\\s){1}(\\d+)(\\s" + CREDITS + "){1}");
		PriceList priceList = new PriceList(metalsAndDirt);

		int lineIndex = 0;
		for (String inputLine : intergalacticInputLines) {
			Matcher matcher = p2.matcher(inputLine);
			while (matcher.find()) {

				String intergallacticAmount = matcher.group(1);
				String merchandise = matcher.group(2);
				String price = matcher.group(4);
				// keep track of lines that were successfully processed
				successMap.put(lineIndex, true);
				/*
				 * System.out.println(inputLine + "[" + intergallacticAmount + " of  " + merchandise
				 * + ": " + price + "]");
				 */
				priceList.addMerchandisePrices(intergallacticAmount, merchandise, price);
			}
			lineIndex++;
		}

		return priceList;
	}

	public static List<String> readInput(String fileName) {
		BufferedReader br = null;
		List<String> inputLines = new ArrayList<String>();
		String line;
		try {
			br = new BufferedReader(new FileReader(fileName));
			line = br.readLine();
			while (line != null) {
				// sb.append(line);
				// sb.append(System.lineSeparator());
				inputLines.add(line);
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return inputLines;// sb != null ? sb.toString() : null;
	}

}
