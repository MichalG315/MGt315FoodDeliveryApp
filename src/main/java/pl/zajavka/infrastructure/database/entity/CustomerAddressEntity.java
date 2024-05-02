package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "customerAddressId")
@ToString(of = {"customerAddressId"})
@Getter
@Setter
@Table(name = "customer_address")
public class CustomerAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_address_id")
    private Integer customerAddressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_extended_id")
    private AddressExtendedEntity addressExtended;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
