package pl.pirog.datacollector.fetcher.ceneo;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.jsoup.nodes.Element;
import pl.pirog.datacollector.model.Image;
import pl.pirog.datacollector.fetcher.supplier.ceneo.CeneoURIResolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Optional;

class ImageFetcher {
    private static final String TEMP_FILE_PREFIX = "dataCollectorTempImg";
    private static final String TEMP_FILE_SUFFIX = ".tmp";
    private final Element productRowDiv;
    private final String imageSelector;
    private final String imageMainUrlSelector;
    private final String imageAlternativeUrlSelector;

    ImageFetcher(Element productRowDiv, String imageSelector, String imageMainUrlSelector, String imageAlternativeUrlSelector) {
        this.productRowDiv = productRowDiv;
        this.imageSelector = imageSelector;
        this.imageMainUrlSelector = imageMainUrlSelector;
        this.imageAlternativeUrlSelector = imageAlternativeUrlSelector;
    }

    Optional<Image> fetch() {
        Element imgElement = productRowDiv.select(imageSelector).first();
        String imageUrl = imgElement.hasAttr(imageAlternativeUrlSelector) ?
                imgElement.attr(imageAlternativeUrlSelector) :
                imgElement.attr(imageMainUrlSelector);
        try {
            return Optional.of(new Image(saveImage(imageUrl), getOriginalFilename(imageUrl)));
        } catch (FileNotFoundException e) {
            System.out.printf("WARNING: Could not find image for %s%n", imageUrl);
            return Optional.empty();
        }
    }

    @SneakyThrows
    private File saveImage(String imageUrl) throws FileNotFoundException {
        InputStream is = CeneoURIResolver.resolve(imageUrl).toURL().openStream();
        File downloadedTempFile = getDestinationPath();
        downloadedTempFile.deleteOnExit();
        IOUtils.copy(is, new FileOutputStream(downloadedTempFile));
        return downloadedTempFile;
    }

    @SneakyThrows
    private File getDestinationPath() {
        return File.createTempFile(TEMP_FILE_PREFIX, TEMP_FILE_SUFFIX);
    }

    private String getOriginalFilename(String imageUrl) {
        String[] splitted = imageUrl.split("/");
        return splitted[splitted.length - 1];
    }
}
