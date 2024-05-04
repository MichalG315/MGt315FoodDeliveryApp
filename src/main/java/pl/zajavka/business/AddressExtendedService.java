package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Restaurant;

@Service
@AllArgsConstructor
public class AddressExtendedService {

    public AddressExtended buildExtenderAddress(Restaurant restaurant) {
        return AddressExtended.builder()
                .streetNumber(restaurant.getAddressStreetNumber())
                .build();
    }
}
