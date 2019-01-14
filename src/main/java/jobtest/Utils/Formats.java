package jobtest.Utils;

public enum Formats {

    F1("^(D)\\s+([a-zA-Z\\s]+)\\s*,\\s*([a-zA-Z\\s]+)\\s*,\\s*(\\d{8}[A-Z])$"),
    F2("^(D)\\s+([a-zA-Z\\s]+)\\s*;\\s*([a-zA-Z\\s]+)\\s*;\\s*(\\d{8}(-)[A-Z])$");

    private final String format;

    Formats(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    /**
     * Verify if typeStringFormat exist
     *
     * @param typeStringFormat the type as string
     * @return boolean
     */
    public static boolean existFormat(String typeStringFormat) {
        for (Formats type : values())
            if (type.name().equals(typeStringFormat))
                return true;
        return false;
    }
}
