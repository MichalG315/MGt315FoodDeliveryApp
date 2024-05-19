package pl.zajavka.domain;

import lombok.*;

import java.util.Set;

@Value
@Builder
@EqualsAndHashCode(of = "email")
@ToString(of = {"name", "surname", "email", "phone"})
public class Customer {
    String name;
    String surname;
    @With
    String email;
    String phone;
    @With
    Integer userId;
    Set<CustomerAddress> customerAddresses;
    Set<Order> orders;
}
