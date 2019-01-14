package jobtest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jobtest.Utils.Parameters;
import jobtest.Utils.ParseLogUtil;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("([a-zA-Z]+)=([\\-\\w\\s.:\\\\]+)");
        Map<Parameters, String> parameters = new HashMap<>();

        String fileDir = "";

        // Build parameters for execution
        for (String arg : args) {
            Matcher matcher = pattern.matcher(arg);
            if (matcher.find() && Parameters.existParameter(matcher.group(1)))
                if (Parameters.valueOf(matcher.group(1).toUpperCase()) == Parameters.FILE)
                    fileDir = matcher.group(2);
                else
                    parameters.put(Parameters.valueOf(matcher.group(1).toUpperCase()), matcher.group(2));
        }

        try {
            // Execute evaluate the ParseLog
            ParseLogUtil.from(fileDir, parameters).eval();
        } catch (IOException e) {
            System.out.println("The file not exist");
        }
    }

}
