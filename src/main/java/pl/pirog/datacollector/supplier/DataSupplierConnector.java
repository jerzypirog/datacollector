package pl.pirog.datacollector.supplier;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class DataSupplierConnector {

    public static Connection connect(String url) {
        System.out.printf("Connecting to page %s%n", url);
        return Jsoup.connect(url).validateTLSCertificates(true);
    }
}
