package pl.zajavka.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "addressExtendedId")
@ToString(of = {"addressExtendedId", "streetNumber"})
public class AddressExtended {

    Integer addressExtendedId;
    String streetNumber;
    Address address;
    Restaurant restaurant;
    Set<CustomerAddress> customerAddresses;

}
