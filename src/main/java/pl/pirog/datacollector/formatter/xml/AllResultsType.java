package pl.pirog.datacollector.formatter.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllResultsType", propOrder = {
        "result"
})
public class AllResultsType {

    private final List<SingleResultsType> result;

    AllResultsType(List<SingleResultsType> result) {
        this.result = result;
    }

    public List<SingleResultsType> getResult() {
        return this.result;
    }

}
