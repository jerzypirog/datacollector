package pl.pirog.datacollector.fetcher.ceneo;

class RowViewSelector implements ElementSelector {
    @Override
    public String forProduct() {
        return "div.cat-prod-row-body";
    }

    @Override
    public String forProductName() {
        return "strong.cat-prod-row-name > a";
    }

    @Override
    public String forMinimalPrice() {
        return "span.price";
    }

    @Override
    public String forPriceValue() {
        return "span.value";
    }

    @Override
    public String forPricePenny() {
        return "span.penny";
    }

    @Override
    public String forImage() {
        return "div.cat-prod-row-foto > a > img";
    }

    @Override
    public String forImageMainUrl() {
        return "src";
    }

    @Override
    public String forImageAlternativeUrl() {
        return "data-original";
    }
}
