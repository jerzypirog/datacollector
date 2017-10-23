package pl.pirog.datacollector.formatter.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SingleResultsType", propOrder = {
        "name",
        "minCost",
        "image"
})
class SingleResultsType {

    @XmlElement(required = true)
    private final String name;
    @XmlElement(name = "min-cost", required = true)
    private final BigDecimal minCost;
    @XmlElement(required = true)
    private final ImageType image;

    SingleResultsType(String name, BigDecimal minCost, ImageType image) {
        this.name = name;
        this.minCost = minCost;
        this.image = image;
    }

}
