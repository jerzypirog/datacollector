package pl.pirog.datacollector.fetcher.ceneo;

import lombok.Getter;
import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.pirog.datacollector.supplier.DataSupplierConnector;
import pl.pirog.datacollector.supplier.ceneo.CeneoURIResolver;

import java.util.Optional;

class Pager {
    private static final String NEXT_PAGE_ELEMENT = "div.pagination > ul > li.arrow-next > a";
    private Optional<String> nextPageHref;
    @Getter
    private Document actualPage;
    @Getter
    private Integer pageCounter = 1;

    @SneakyThrows
    Pager(String firstPageUrl) {
        actualPage = DataSupplierConnector.connect(firstPageUrl).get();
    }

    boolean hasNextPage() {
        Elements nextPageSelector = this.getActualPage().select(NEXT_PAGE_ELEMENT);
        if (nextPageSelector.isEmpty()) return false;
        nextPageHref = Optional.of(nextPageSelector.first().attr("href"));
        nextPage();
        return true;
    }

    @SneakyThrows
    private void nextPage() {
        if (nextPageHref.isPresent()) {
            actualPage = DataSupplierConnector.connect(getNextPageURL()).get();
            pageCounter++;
        }
    }

    private String getNextPageURL() {
        return CeneoURIResolver.resolve(nextPageHref.get()).toString();
    }
}
