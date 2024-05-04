package pl.zajavka.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "email")
@ToString(of = {"customerId", "name", "surname", "email", "phone"})
public class Customer {

    Integer customerId;
    String name;
    String surname;
    String email;
    String phone;
    Integer userId;
    Set<CustomerAddress> customerAddresses;
    Set<FoodOrder> foodOrders;

}
