package pl.zajavka.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "email")
@ToString(of = {"name", "surname", "email", "phone"})
public class Customer {
    String name;
    String surname;
    String email;
    String phone;
    Integer userId;
    Set<CustomerAddress> customerAddresses;
    Set<Order> orders;
}
