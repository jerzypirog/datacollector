package pl.pirog.datacollector.formatter;

import pl.pirog.datacollector.model.ProductItem;

import java.util.List;

public interface OutputFormatter {
    void format(List<ProductItem> items);
}
