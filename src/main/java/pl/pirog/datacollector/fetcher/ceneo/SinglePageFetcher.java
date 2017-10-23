package pl.pirog.datacollector.fetcher.ceneo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.pirog.datacollector.model.ProductItem;

import java.util.ArrayList;
import java.util.List;

class SinglePageFetcher {
    private final Document document;
    private final ElementSelector selector;

    SinglePageFetcher(Document document) {
        this.document = document;
        this.selector = new ElementSelectorResolver(document).getSelector();
    }

    List<ProductItem> get() {
        List<ProductItem> items = new ArrayList<>();
        Elements productRowDivs = document.select(selector.forProduct());
        for (Element productRowDiv : productRowDivs) {
            items.add(new ProductItem(new ProductNameFetcher(productRowDiv, selector.forProductName()).fetch(),
                    new MinimalPriceFetcher(productRowDiv, selector.forMinimalPrice(), selector.forPriceValue(), selector.forPricePenny()).fetch(),
                    new ImageFetcher(productRowDiv, selector.forImage(), selector.forImageMainUrl(), selector.forImageAlternativeUrl()).fetch()));
        }
        return items;
    }
}
