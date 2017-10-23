package pl.pirog.datacollector.fetcher.ceneo;

import pl.pirog.datacollector.fetcher.DataSupplierFetcher;
import pl.pirog.datacollector.model.ProductItem;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataFetcher implements DataSupplierFetcher {
    private final Pager ceneoPager;
    private ZonedDateTime start;
    private ZonedDateTime end;

    public DataFetcher(String firstPageUrl) {
        this.ceneoPager = new Pager(firstPageUrl);
    }

    @Override
    public List<ProductItem> fetch() {
        markStarted();
        List<ProductItem> results = new ArrayList<>();
        do {
            printProcessingPageNumber(ceneoPager.getPageCounter());
            results.addAll(new SinglePageFetcher(ceneoPager.getActualPage()).get());
        } while (ceneoPager.hasNextPage());
        markEnded();
        printDuration();
        return results;
    }

    private void markStarted() {
        start = ZonedDateTime.now(ZoneId.systemDefault());
        System.out.printf("Start %s%n", start);
    }

    private void markEnded() {
        end = ZonedDateTime.now(ZoneId.systemDefault());
        System.out.printf("End %s%n", end);
    }

    private void printDuration() {
        Duration duration = Duration.between(start, end);
        long s = duration.getSeconds();
        System.out.printf("Fetching duration %d:%02d:%02d%n", s / 3600, (s % 3600) / 60, (s % 60));
    }

    private void printProcessingPageNumber(Integer pageCounter) {
        System.out.printf("Processing page no. %d%n", pageCounter);
    }
}
