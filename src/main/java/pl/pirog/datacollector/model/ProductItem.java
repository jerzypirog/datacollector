package pl.pirog.datacollector.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
public class ProductItem {
    private final String name;
    private final BigDecimal minimalCost;
    private final Optional<Image> image;

    public ProductItem(String name, BigDecimal minimalCost, Optional<Image> image) {
        this.name = name;
        this.minimalCost = minimalCost;
        this.image = image;
    }
}