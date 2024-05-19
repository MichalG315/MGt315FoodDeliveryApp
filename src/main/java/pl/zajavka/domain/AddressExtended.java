package pl.zajavka.domain;

import lombok.*;

import java.util.Set;

@Value
@Builder
@EqualsAndHashCode(of = {"addressExtendedId", "streetNumber", "address"})
@ToString(of = {"addressExtendedId", "streetNumber"})
public class AddressExtended {
    Integer addressExtendedId;
    String streetNumber;
    Address address;
    Restaurant restaurant;
    Set<CustomerAddress> customerAddresses;
}
