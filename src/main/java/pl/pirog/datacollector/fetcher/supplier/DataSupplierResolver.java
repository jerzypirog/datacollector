package pl.pirog.datacollector.fetcher.supplier;

import lombok.SneakyThrows;
import pl.pirog.datacollector.fetcher.DataSupplierFetcher;
import pl.pirog.datacollector.fetcher.ceneo.DataFetcher;
import pl.pirog.datacollector.fetcher.supplier.ceneo.CeneoURIResolver;

public class DataSupplierResolver {
    @SneakyThrows
    public DataSupplierFetcher createFetcher(String url) {
        switch (DataSupplier.find(url)) {
            case CENEO:
                return new DataFetcher(url);
        }
        throw new IllegalArgumentException(String.format("Unsupported fetcher for URL address: %s%n", url));
    }

    enum DataSupplier {
        CENEO(new CeneoURIResolver());

        private final DataSupplierURIResolver uriResolver;

        @SneakyThrows
        DataSupplier(DataSupplierURIResolver uriResolver) {
            this.uriResolver = uriResolver;
        }

        @SneakyThrows
        static DataSupplier find(String url) {
            for (DataSupplier dataSupplier : values()) {
                for (String host : dataSupplier.uriResolver.getHosts()) {
                    if (host.equalsIgnoreCase(dataSupplier.uriResolver.getHostForUrl(url))) return dataSupplier;
                }
            }
            throw new IllegalArgumentException(String.format("Unknown data supplier for URL: %s", url));
        }
    }
}
