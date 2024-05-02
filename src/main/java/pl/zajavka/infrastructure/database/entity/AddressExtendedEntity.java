package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "addressExtendedId")
@ToString(of = {"addressExtendedId", "streetNumber"})
@Getter
@Setter
@Table(name = "address_extended")
public class AddressExtendedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_extended_id")
    private Integer addressExtendedId;

    @Column(name = "street_number")
    private String streetNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "addressExtended")
    private RestaurantEntity restaurantEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "addressExtended")
    private Set<CustomerAddressEntity> customerAddresses;

}
