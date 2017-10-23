package pl.pirog.datacollector.fetcher.supplier;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public interface DataSupplierURIResolver {
    default String getHostForUrl(String url) throws URISyntaxException {
        return new URI(fixURL(url)).getHost();
    }

    default String fixURL(String url) {
        if (!url.startsWith("http") && !url.startsWith("https")) {
            url = "http://" + url;
        }
        return url;
    }

    List<String> getHosts() throws URISyntaxException;
}
