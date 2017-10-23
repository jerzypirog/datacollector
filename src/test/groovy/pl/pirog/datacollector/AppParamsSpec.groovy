package pl.pirog.datacollector

import com.google.common.collect.Lists
import org.apache.commons.cli.MissingOptionException
import org.apache.commons.cli.UnrecognizedOptionException
import pl.pirog.datacollector.formatter.OutputFormatResolver
import spock.lang.Specification

class AppParamsSpec extends Specification {
    private static final String DEFAULT_OUTPUT = "results.xml"
    private static final String TEST_EXAMPLE_OUTPUT = "test/test.xml"
    private static final String CENEO_URL_EXAMPLE = "http://www.ceneo.pl/Gitary/p:Yamaha_Music/Rodzaj:Elektryczne.htm"

    def "should parse -u parameter to ceneo category page and -o parameter with result file name"() {
        String[] args = prepareArgs(u, o, f)
        given:
        AppParams params = AppParams.parseArgs(args)
        expect:
        params.url == url
        params.output == output
        params.format == outputFormat
        where:
        u                                         | o                                           | f                                                                    || url               | output              | outputFormat
        String.format("-u=%s", CENEO_URL_EXAMPLE) | null                                        | null                                                                 || CENEO_URL_EXAMPLE | DEFAULT_OUTPUT      | OutputFormatResolver.OutputFormat.XML
        String.format("-u=%s", CENEO_URL_EXAMPLE) | null                                        | String.format("-f=%s", OutputFormatResolver.OutputFormat.XML.name()) || CENEO_URL_EXAMPLE | DEFAULT_OUTPUT      | OutputFormatResolver.OutputFormat.XML
        String.format("-u=%s", CENEO_URL_EXAMPLE) | String.format("-o=%s", TEST_EXAMPLE_OUTPUT) | null                                                                 || CENEO_URL_EXAMPLE | TEST_EXAMPLE_OUTPUT | OutputFormatResolver.OutputFormat.XML
        String.format("-u=%s", CENEO_URL_EXAMPLE) | String.format("-o=%s", TEST_EXAMPLE_OUTPUT) | String.format("-f=%s", OutputFormatResolver.OutputFormat.XML.name()) || CENEO_URL_EXAMPLE | TEST_EXAMPLE_OUTPUT | OutputFormatResolver.OutputFormat.XML
    }

    def "should throw exception when format is unsupported"() {
        String[] args = prepareArgs(u, null, f)
        when:
        AppParams.parseArgs(args)
        then:
        def error = thrown(IllegalArgumentException)
        error.message.startsWith("No enum constant")
        where:
        u                                         | f
        String.format("-u=%s", CENEO_URL_EXAMPLE) | String.format("-f=%s", "TEST_FORMAT")
    }

    def "should throw exception when param -u is not provided"() {
        String[] args = prepareArgs(u, o, f)
        when:
        AppParams.parseArgs(args)
        then:
        def error = thrown(MissingOptionException)
        error.message == "Missing required option: u"

        where:
        u    | o                                           | f
        null | null                                        | null
        null | null                                        | String.format("-o=%s", TEST_EXAMPLE_OUTPUT)
        null | String.format("-o=%s", TEST_EXAMPLE_OUTPUT) | null
        null | String.format("-o=%s", TEST_EXAMPLE_OUTPUT) | String.format("-o=%s", TEST_EXAMPLE_OUTPUT)
    }

    def "should throw exception when unknown parametr is provided"() {
        String[] args = prepareArgs(String.format("-s=%s", "test"))
        when:
        AppParams.parseArgs(args)
        then:
        def error = thrown(UnrecognizedOptionException)
        error.message.startsWith("Unrecognized option")
    }

    private static List<String> prepareArgs(String[] args) {
        List<String> argsList = new ArrayList()
        for (String arg : args){
            if (arg != null) argsList.add(arg)
        }
        argsList
    }

}
