//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.10.16 at 10:54:09 PM CEST 
//


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