package jobtest;

import jobtest.Utils.Parameters;
import jobtest.Utils.ParseLogUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class ParseLogUtilTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> getData() {
        Map<Parameters, String> params1 = new HashMap<>();
        params1.put(Parameters.ID, "a");

        Map<Parameters, String> params2 = new HashMap<>();
        params2.put(Parameters.ID, "93654902Z");

        Map<Parameters, String> params3 = new HashMap<>();
        params3.put(Parameters.ID, "93654902Z");

        Map<Parameters, String> value1 = new HashMap<>();
        value1.put(Parameters.CITY, "BARCELONA");
        value1.put(Parameters.ID, "93654902Z");
        value1.put(Parameters.NAME, "Erica Burns");

        Map<Parameters, String> value2 = new HashMap<>();

        Map<Parameters, String> value3 = new HashMap<>();
        value3.put(Parameters.CITY, "BARCELONA");
        value3.put(Parameters.ID, "93654902Z");
        value3.put(Parameters.NAME, "Erica Burns");

        return Arrays.asList(new Object[][]{
                {"D Erica Burns,BARCELONA,93654902Z", params1, false, false, value1},
                {"F2", params2, true, false, value2},
                {"D Erica Burns,BARCELONA,93654902Z", params3, false, true, value3}
        });
    }

    private ParseLogUtil parse;
    private String line;
    private Map<Parameters, String> params;

    private boolean changeValue;
    private boolean filterValue;
    private Map<Parameters, String> mapFromLineValue;

    public ParseLogUtilTest(String line, Map<Parameters, String> params,
                            boolean changeValue, boolean filterValue, Map<Parameters, String> mapFromLineValue) {
        this.line = line;
        this.params = params;

        this.changeValue = changeValue;
        this.filterValue = filterValue;
        this.mapFromLineValue = mapFromLineValue;
    }

    @Before
    public void before() {
        Stream<String> source = Stream.of(this.line);
        this.parse = new ParseLogUtil(source, this.params);
    }

    @Test
    public void testChangeFormat() {
        assertEquals(parse.changeFormat(line), changeValue);
    }

    @Test
    public void testFilter() {
        assertEquals(parse.filter(line), filterValue);
    }

    @Test
    public void testGetMapFromLine() {
        assertEquals(parse.getMapFromLine(line), mapFromLineValue);
    }
}
