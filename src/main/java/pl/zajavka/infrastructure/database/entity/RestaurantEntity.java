package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {"restaurantId", "restaurantName", "description"})
@Getter
@Setter
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_extended_id")
    private AddressExtendedEntity addressExtended;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<RestaurantDeliveryAddressEntity> restaurantDeliveryAddresses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<MenuItemEntity> menuItems;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<FoodOrderEntity> foodOrders;

}
