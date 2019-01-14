package jobtest.Utils;

public enum Parameters {
    FILE,

    ID,
    NAME,
    CITY;

    /**
     * Verify if typeStringFormat exist
     *
     * @param typeStringFormat the type as string
     * @return boolean
     */
    public static boolean existParameter(String typeStringFormat) {
        for (Parameters type : values())
            if (type.name().equals(typeStringFormat))
                return true;
        return false;
    }
}
