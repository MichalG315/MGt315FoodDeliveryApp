package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "addressId")
@ToString(of = {"addressId", "country", "city", "postalCode", "streetName"})
@Getter
@Setter
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "street_name")
    private String streetName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
    private Set<AddressExtendedEntity> addressesExtended;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
    private Set<RestaurantDeliveryAddressEntity> restaurantDeliveryAddresses;

}
