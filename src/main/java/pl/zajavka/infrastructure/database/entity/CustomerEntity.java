package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "customerId")
@ToString(of = {"customerId", "name", "surname", "email", "phone"})
@Getter
@Setter
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "user_id")
    private Integer userId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<CustomerAddressEntity> customerAddresses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<FoodOrderEntity> foodOrders;

}
