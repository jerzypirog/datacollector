package pl.pirog.datacollector.fetcher;

import pl.pirog.datacollector.model.ProductItem;

import java.util.List;

public interface DataSupplierFetcher {
    List<ProductItem> fetch();
}
