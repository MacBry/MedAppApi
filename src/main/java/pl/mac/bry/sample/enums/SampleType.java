package pl.mac.bry.sample.enums;

public enum SampleType {
    SERUM_SAMPLE("Serum sample"),
    PLASMA_SAMPLE("Plasma sample"),
    BLOOD_SAMPLE("Blood sample"),
    URINE_SAMPLE("Urine sample");

    private final String description;

    SampleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
