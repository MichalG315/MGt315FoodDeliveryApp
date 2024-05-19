package pl.zajavka.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(of = "customerAddressId")
@ToString(of = {"customerAddressId"})
public class CustomerAddress {
    Integer customerAddressId;
    AddressExtended addressExtended;
    Customer customer;
}
