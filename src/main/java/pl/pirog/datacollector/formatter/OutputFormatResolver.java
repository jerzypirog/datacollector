package pl.pirog.datacollector.formatter;

import lombok.AllArgsConstructor;
import pl.pirog.datacollector.formatter.xml.XmlFormatter;

@AllArgsConstructor
public class OutputFormatResolver {
    private final OutputFormat outputFormat;

    public OutputFormatter createConverter(String url, String destination) {
        switch (outputFormat) {
            case XML:
                return new XmlFormatter(url, destination);
        }
        throw new IllegalArgumentException(String.format("Unsupported format %s%n", outputFormat.name()));
    }

    public enum OutputFormat {
        XML
    }

}
