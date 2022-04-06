package pl.mac.bry.referral_unit;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.mac.bry.order.Order;
import pl.mac.bry.unit_address.UnitAddress;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReferralUnit implements Serializable {

    private static final long serialVersionUID = 1548618989609648406L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;

    private String shortName;

    private long nipNumber;

    private long regonNumber;

    private String email;

    private String resortBookNumber;

    @OneToMany(mappedBy = "referralUnit", cascade =  { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)// to remove "cannot simultaneously fetch multiple bags"
    private Set<Order> orders;

    @OneToOne
    private UnitAddress unitAddress;

    public void addOrder(Order order) {
        orders.add(order);
    }
}
