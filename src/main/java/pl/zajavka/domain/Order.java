package pl.zajavka.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Map;

@With
@Value
@Builder
public class Order {

    String existingCustomerEmail;
    String customerName;
    String customerSurname;
    String customerPhone;
    String customerEmail;
    String customerAddressCountry;
    String customerAddressCity;
    String customerAddressPostalCode;
    String customerAddressStreetName;
    String customerAddressStreetNumber;
    String customerComment;
    Map<String, Integer> foodItemNumberAndQuantity;


}
