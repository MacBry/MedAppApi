package pl.mac.bry.patient.enums;

public enum RhDFactor {
	RHD_POSITIVE("RhD positive"),
	RHD_NEGATIVE("RhD negative");
	
	private final String description;

	private RhDFactor(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	
}
