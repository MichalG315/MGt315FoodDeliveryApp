package pl.zajavka.business;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.util.DomainFixtures;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Test
    void buildAddress() {
        // given
        Address address = DomainFixtures.someAddress1();
        AddressExtended addressExtended = DomainFixtures.someAddressExtended1(address);
        Restaurant restaurant = DomainFixtures.someRestaurant3(addressExtended);

        // when
        Address result = addressService.buildAddress(restaurant);

        // then
        Assertions.assertThat(result).isEqualTo(address);
    }
}