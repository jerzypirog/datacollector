package pl.pirog.datacollector.fetcher.ceneo;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

class MinimalPriceFetcher {
    private final Element productRowDiv;
    private final String minimalPriceSelector;
    private final String priceValueSelector;
    private final String pricePennySelector;

    MinimalPriceFetcher(Element productRowDiv, String minimalPriceSelector, String priceValueSelector, String pricePennySelector) {
        this.productRowDiv = productRowDiv;
        this.minimalPriceSelector = minimalPriceSelector;
        this.priceValueSelector = priceValueSelector;
        this.pricePennySelector = pricePennySelector;
    }

    BigDecimal fetch() {
        Elements priceSpan = productRowDiv.select(minimalPriceSelector);
        String price = priceSpan.select(priceValueSelector).first().childNode(0).toString() +
                priceSpan.select(pricePennySelector).first().childNode(0).toString();
        return new BigDecimal(price.replace(",", "."));
    }
}
