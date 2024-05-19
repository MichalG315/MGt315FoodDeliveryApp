package pl.zajavka.domain;

import lombok.*;

@Value
@Builder
@EqualsAndHashCode(of = "customerAddressId")
@ToString(of = {"customerAddressId"})
public class CustomerAddress {
    Integer customerAddressId;
    AddressExtended addressExtended;
    Customer customer;
}
