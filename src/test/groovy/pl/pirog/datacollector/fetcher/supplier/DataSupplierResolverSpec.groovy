package pl.pirog.datacollector.fetcher.supplier

import spock.lang.Specification

class DataSupplierResolverSpec extends Specification {
    def "should find appropriate data supplier for URIs"() {
        when:
        DataSupplierResolver.DataSupplier supplier = DataSupplierResolver.DataSupplier.find(url)
        then:
        noExceptionThrown()
        supplier.equals(returnedSupplier)
        where:
        url                                                                        || returnedSupplier
        "https://www.ceneo.pl/Filmy_Blu-ray/Gatunek:Sensacyjne;0020-30-0-0-19.htm" || DataSupplierResolver.DataSupplier.CENEO
        "http://www.ceneo.pl/Filmy_Blu-ray/Gatunek:Sensacyjne;0020-30-0-0-19.htm"  || DataSupplierResolver.DataSupplier.CENEO
        "ceneo.pl/Filmy_Blu-ray/Gatunek:Sensacyjne;0020-30-0-0-19.htm"             || DataSupplierResolver.DataSupplier.CENEO
        "www.ceneo.pl/Filmy_Blu-ray/Gatunek:Sensacyjne;0020-30-0-0-19.htm"         || DataSupplierResolver.DataSupplier.CENEO
        "https://ceneo.pl/Filmy_Blu-ray/Gatunek:Sensacyjne;0020-30-0-0-19.htm"     || DataSupplierResolver.DataSupplier.CENEO
        "http://ceneo.pl/Filmy_Blu-ray/Gatunek:Sensacyjne;0020-30-0-0-19.htm"      || DataSupplierResolver.DataSupplier.CENEO
    }

    def "should throw exception when data supplier domain is unsupported"() {
        when:
        DataSupplierResolver.DataSupplier.find(url)
        then:
        thrown(expectedException)
        where:
        url                                      || expectedException
        "https://web.archive.org/web/*/ceneo.pl" || IllegalArgumentException
    }
}