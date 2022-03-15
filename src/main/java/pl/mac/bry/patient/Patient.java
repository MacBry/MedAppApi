package pl.mac.bry.patient;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import pl.mac.bry.patient.enums.ABOBloodGroup;
import pl.mac.bry.patient.enums.RhDFactor;

@Data
@Entity
class Patient implements Serializable  {


	private static final long serialVersionUID = 3887440738936426539L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	private String firstName;
	
	private String lastName;
	
	private String pesel;
	
	private ABOBloodGroup aboGroup;
	
	private RhDFactor rhdFactor;

}
