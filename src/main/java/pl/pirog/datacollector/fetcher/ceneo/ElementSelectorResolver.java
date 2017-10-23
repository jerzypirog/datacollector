package pl.pirog.datacollector.fetcher.ceneo;

import lombok.Getter;
import org.jsoup.nodes.Document;

class ElementSelectorResolver {
    @Getter
    private final ElementSelector selector;

    ElementSelectorResolver(Document document) {
        this.selector = getRightSelector(document);
    }

    private ElementSelector getRightSelector(Document document) {
        return document.select(new TableViewSelector().forProduct()).isEmpty() ? new RowViewSelector() : new TableViewSelector();
    }
}
