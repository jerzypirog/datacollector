package pl.pirog.datacollector.fetcher.ceneo;

class TableViewSelector implements ElementSelector {
    @Override
    public String forProduct() {
        return "div.category-item-box";
    }

    @Override
    public String forProductName() {
        return "strong.category-item-box-name > a";
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
        return "div.category-item-box-picture > a > img";
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
