//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.10.16 at 10:54:09 PM CEST 
//


package pl.pirog.datacollector.formatter.xml;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImageType", propOrder = {
        "value"
})
class ImageType {

    @XmlValue
    private final byte[] value;
    @XmlAttribute(name = "original_name")
    private final String originalName;

    ImageType(byte[] value, String originalName) {
        this.value = value;
        this.originalName = originalName;
    }
}
