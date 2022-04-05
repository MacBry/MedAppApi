package pl.mac.bry.patient;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import pl.mac.bry.patient_address.PatientAddress;
import pl.mac.bry.order.Order;


@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Patient implements Serializable  {


	private static final long serialVersionUID = 3887440738936426539L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	private String firstName;
	
	private String lastName;
	
	private String pesel;

	@OneToMany(mappedBy = "patient", cascade =  { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)// to remove "cannot simultaneously fetch multiple bags"
	private Set<PatientAddress> patientAddresses = new HashSet<>();

	@OneToMany(mappedBy = "patient", cascade =  { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)// to remove "cannot simultaneously fetch multiple bags"
	private Set<Order> orders =new HashSet<>();

	

}
