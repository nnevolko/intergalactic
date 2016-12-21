package main.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PriceList {

	private Map<String, QuantityPrice> priceList = new HashMap<String, QuantityPrice>();
	private Set<String> stockMerchandise = new HashSet<>();

	public PriceList(List<String> merchandise) {
		this.stockMerchandise.addAll(merchandise);
	}

	public void addMerchandisePrices(String integallacticAmount, String merchandise,
			String priceCredits) {

		merchandise = merchandise.trim();
		/*if (!stockMerchandise.contains(merchandise)) {
			System.out.println("We only sell metals or dirt!"); // TODO throw custom exception
			return;
		}*/

		integallacticAmount = integallacticAmount.trim();
		priceCredits = priceCredits.trim();

		Integer price = Integer.parseInt(priceCredits);
		QuantityPrice qp = new QuantityPrice(integallacticAmount, price);

		priceList.put(merchandise, qp);
	}

	public QuantityPrice getMerchandiseCost(String merchandise) {
		merchandise = merchandise.trim();
		return priceList.get(merchandise);
	}

	@Override
	public String toString() {
		StringBuffer sf = new StringBuffer();
		sf.append("PriceList {");
		for (Map.Entry<String, QuantityPrice> entry : priceList.entrySet()) {
			sf.append("\n\t" + entry.getKey() + ":" + entry.getValue());
		}
		sf.append("\n}");
		return sf.toString();
	}

}
