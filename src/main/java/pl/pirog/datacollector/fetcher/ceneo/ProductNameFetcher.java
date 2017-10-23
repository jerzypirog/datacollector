package pl.pirog.datacollector.fetcher.ceneo;

import org.jsoup.nodes.Element;

class ProductNameFetcher {
    private final Element productRowDiv;
    private final String productNameSelector;

    ProductNameFetcher(Element productRowDiv, String productNameSelector) {
        this.productRowDiv = productRowDiv;
        this.productNameSelector = productNameSelector;
    }

    String fetch() {
        return productRowDiv.select(productNameSelector)
                .first()
                .childNode(0)
                .toString();
    }
}
