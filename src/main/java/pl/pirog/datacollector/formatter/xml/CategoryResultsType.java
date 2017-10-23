package pl.pirog.datacollector.formatter.xml;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategoryResultsType", propOrder = {
        "results",
        "generated",
        "url"
})
class CategoryResultsType {

    @XmlElement(required = true)
    private final AllResultsType results;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    private final XMLGregorianCalendar generated;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    private final String url;

    CategoryResultsType(AllResultsType results, XMLGregorianCalendar generated, String url) {
        this.results = results;
        this.generated = generated;
        this.url = url;
    }

    public AllResultsType getResults() {
        return results;
    }

    public XMLGregorianCalendar getGenerated() {
        return generated;
    }

    public String getUrl() {
        return url;
    }
}
