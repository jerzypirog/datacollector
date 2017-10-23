package pl.pirog.datacollector;

import lombok.Getter;
import org.apache.commons.cli.*;
import pl.pirog.datacollector.formatter.OutputFormatResolver;

import java.util.Optional;

@Getter
class AppParams {
    public static final String DEFAULT_RESULT_FILENAME = "results.xml";
    @Getter
    private static final Options options = new Options();

    static {
        options.addOption(Option.builder("u")
                .hasArg()
                .desc("category url")
                .longOpt("url")
                .required()
                .build());
        options.addOption(Option.builder("o")
                .hasArg()
                .desc("path where will be saved output file")
                .longOpt("output")
                .build());
        options.addOption(Option.builder("f")
                .hasArg()
                .desc("output format")
                .longOpt("format")
                .build());

    }

    private final CommandLine cmd;
    private String url;
    private String output;
    private OutputFormatResolver.OutputFormat format;

    private AppParams(CommandLine cmd) {
        this.cmd = cmd;
    }

    public static AppParams parseArgs(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        AppParams params = new AppParams(cmd);
        params.url = params.parseUrl();
        params.output = params.parseOutput();
        params.format = OutputFormatResolver.OutputFormat.valueOf(params.parseFormat());
        return params;
    }

    private String parseUrl() {
        return cmd.getOptionValue("u");
    }

    private String parseOutput() {
        return Optional.ofNullable(cmd.getOptionValue("o")).orElse(DEFAULT_RESULT_FILENAME);
    }

    private String parseFormat() {
        return Optional.ofNullable(cmd.getOptionValue("f")).orElse(OutputFormatResolver.OutputFormat.XML.name());
    }
}
