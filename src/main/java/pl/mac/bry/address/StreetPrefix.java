package pl.mac.bry.address;

public enum StreetPrefix {
    STREET("St"),
    ROAD("Rd"),
    AVENUE("Ave"),
    BOULEVARD("Bvd"),
    ALLEY("Ally"),
    PLACE("Pl");

    private final String description;

    private StreetPrefix(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static StreetPrefix valuesOfDescription (String description) {
        for(StreetPrefix streetPrefix : values()) {
            if(streetPrefix.getDescription().equals(description)) {
                return streetPrefix;
            }
        }
        return null;
    }

}
