package jobtest.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ParseLogUtil {

    /**
     * Get a ParseLogUtil object from parameters
     *
     * @param fileDir    the file location
     * @param parameters the filters parameters
     * @return ParseLogUtil
     * @throws IOException if the file not exist
     */
    public static ParseLogUtil from(String fileDir, Map<Parameters, String> parameters) throws IOException {
        return new ParseLogUtil(Files.lines(Paths.get(fileDir)), parameters);
    }

    private Stream<String> source;
    private Map<Parameters, String> parameters;
    private Formats format = Formats.F1;

    public ParseLogUtil(Stream<String> source, Map<Parameters, String> parameters) {
        this.source = source;
        this.parameters = parameters;
    }

    /**
     * Apply filters and show the matches
     */
    public void eval() {
        source
                .filter(s -> !s.isEmpty())
                .filter(s -> !changeFormat(s))
                .filter(this::filter)
                .forEach(System.out::println);
    }

    /**
     * Verify if the line is a change format and change it
     *
     * @param format the line to evaluate
     * @return boolean
     */
    public boolean changeFormat(String format) {
        if (Formats.existFormat(format)) {
            this.format = Formats.valueOf(format);
            return true;
        }
        return false;
    }

    /**
     * Verify if line match with parameters
     *
     * @param line the line to filter
     * @return boolean
     */
    public boolean filter(String line) {
        Map<Parameters, String> lineMap = getMapFromLine(line);

        return parameters.keySet().stream()
                .noneMatch((key) -> (lineMap.get(key) == null ||
                        (lineMap.get(key) != null && !lineMap.get(key).equals(parameters.get(key)))));
    }

    /**
     * Map line to a Map object
     *
     * @param line the line to map
     * @return Map<Parameters       ,               String>
     */
    public Map<Parameters, String> getMapFromLine(String line) {
        Map<Parameters, String> map = new HashMap<>();

        Pattern pattern = Pattern.compile(format.getFormat());
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            map.put(Parameters.ID, matcher.group(4).trim());
            map.put(Parameters.NAME, matcher.group(2).trim());
            map.put(Parameters.CITY, matcher.group(3).trim());
        }

        return map;
    }
}
