package pl.pirog.datacollector.fetcher.ceneo;

public interface ElementSelector {
    String forProduct();

    String forProductName();

    String forMinimalPrice();

    String forPriceValue();

    String forPricePenny();

    String forImage();

    String forImageMainUrl();

    String forImageAlternativeUrl();
}
