package pl.pirog.datacollector;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingOptionException;
import pl.pirog.datacollector.fetcher.DataSupplierFetcher;
import pl.pirog.datacollector.formatter.OutputFormatResolver;
import pl.pirog.datacollector.model.ProductItem;
import pl.pirog.datacollector.supplier.DataSupplierResolver;

import java.util.List;

class App {
    public static void main(String[] args) throws Exception {
        try {
            AppParams params = AppParams.parseArgs(args);
            DataSupplierFetcher fetcher = new DataSupplierResolver().createFetcher(params.getUrl());
            List<ProductItem> items = fetcher.fetch();
            new OutputFormatResolver(params.getFormat()).createConverter(params.getUrl(), params.getOutput())
                    .format(items);
        } catch (MissingOptionException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("dataCollector", AppParams.getOptions(), true);
        }
    }
}
