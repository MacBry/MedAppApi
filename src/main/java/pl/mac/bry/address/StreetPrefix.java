package pl.mac.bry.address;

enum StreetPrefix {
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

    String getDescription() {
        return description;
    }

    static StreetPrefix valuesOfDescription (String description) {
        for(StreetPrefix streetPrefix : values()) {
            if(streetPrefix.getDescription().equals(description)) {
                return streetPrefix;
            }
        }
        return null;
    }

}
