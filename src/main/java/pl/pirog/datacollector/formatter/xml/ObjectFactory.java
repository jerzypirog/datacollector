package pl.pirog.datacollector.formatter.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
class ObjectFactory {

    private final static QName _CategoryResults_QNAME = new QName("http://tempuri.org/CategoryResultSchema.xsd", "CategoryResults");

    @XmlElementDecl(namespace = "http://tempuri.org/CategoryResultSchema.xsd", name = "CategoryResults")
    JAXBElement<CategoryResultsType> createCategoryResults(CategoryResultsType value) {
        return new JAXBElement<>(_CategoryResults_QNAME, CategoryResultsType.class, null, value);
    }

}
