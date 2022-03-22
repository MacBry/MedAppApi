package pl.mac.bry.patient.enums;

public enum ABOBloodGroup {
	A("Group A"),
	AB("Group AB"),
	B("Group B"),
	O("Group 0");
	
	private final String description;

	private ABOBloodGroup(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
 	public static ABOBloodGroup valueOfDescription (String description) {
		for (ABOBloodGroup aboBloodGroup : values()) {
			if (aboBloodGroup.getDescription().equals(description)) {
				return aboBloodGroup;
			}
		}
		return null;
	}
}
