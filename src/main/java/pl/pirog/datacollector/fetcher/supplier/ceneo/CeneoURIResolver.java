package pl.pirog.datacollector.fetcher.supplier.ceneo;

import com.google.common.collect.ImmutableList;
import lombok.SneakyThrows;
import pl.pirog.datacollector.fetcher.supplier.DataSupplierURIResolver;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class CeneoURIResolver implements DataSupplierURIResolver {

    @SneakyThrows
    public static URI resolve(String url) {
        URI uri = new URI(url);
        return getBaseURI().resolve(uri);
    }

    private static URI getBaseURI() throws URISyntaxException {
        return new URI("https://ceneo.pl");
    }

    @Override
    public List<String> getHosts() throws URISyntaxException {
        return new ImmutableList.Builder<String>().add(getBaseURI().getHost())
                .add(new URI("https://www.ceneo.pl").getHost())
                .build();
    }
}