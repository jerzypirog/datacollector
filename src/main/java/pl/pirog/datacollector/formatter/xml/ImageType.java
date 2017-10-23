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
