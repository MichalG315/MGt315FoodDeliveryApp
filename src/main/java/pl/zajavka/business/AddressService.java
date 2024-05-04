package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.Restaurant;

@Service
@AllArgsConstructor
public class AddressService {

    public Address buildAddress(Restaurant restaurant) {
        return Address.builder()
                .country(restaurant.getAddressCountry())
                .city(restaurant.getAddressCity())
                .postalCode(restaurant.getAddressPostalCode())
                .streetName(restaurant.getAddressStreetName())
                .build();
    }
}
