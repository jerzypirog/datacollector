package pl.pirog.datacollector.formatter.xml;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import pl.pirog.datacollector.formatter.OutputFormatter;
import pl.pirog.datacollector.model.ProductItem;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class XmlFormatter implements OutputFormatter {

    private final String url;
    private final String destination;


    public XmlFormatter(String url, String destination) {
        this.url = url;
        this.destination = destination;
    }

    @SneakyThrows
    public void format(List<ProductItem> items) {
        AllResultsType allResults = new AllResultsType(getAllResults(items));
        CategoryResultsType resultsType = new CategoryResultsType(allResults, getXmlGregorianCalendar(), url);
        JAXBElement<CategoryResultsType> results = new ObjectFactory().createCategoryResults(resultsType);
        createXmlFile(results);
        System.out.printf("%d items were fetched!%n", items.size());
    }

    @SneakyThrows
    private void createXmlFile(JAXBElement<CategoryResultsType> results) {
        JAXBContext context = JAXBContext.newInstance(CategoryResultsType.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File destinationFile = new File(destination);
        marshaller.marshal(results, destinationFile);
        System.out.printf("Results were succesfully converted and saved to file %s%n", destinationFile.getAbsolutePath());
    }

    private List<SingleResultsType> getAllResults(List<ProductItem> items) throws IOException {
        List<SingleResultsType> results = new ArrayList<>();
        for (ProductItem item : items) {
            ImageType imageType = null;
            if (item.getImage().isPresent()) {
                imageType = new ImageType(IOUtils.toByteArray(item.getImage().get().getInputStream()),
                        item.getImage().get().getOriginalFileName());
            }
            SingleResultsType singleResult = new SingleResultsType(item.getName(), item.getMinimalCost(), imageType);
            results.add(singleResult);
        }
        return results;
    }

    private XMLGregorianCalendar getXmlGregorianCalendar() throws DatatypeConfigurationException {
        Instant now = Instant.now();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(now.toEpochMilli());
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
    }
}
