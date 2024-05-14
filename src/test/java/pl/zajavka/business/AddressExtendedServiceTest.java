package pl.zajavka.business;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.infrastructure.database.repository.util.DomainFixtures;

@ExtendWith(MockitoExtension.class)
class AddressExtendedServiceTest {

    @InjectMocks
    private AddressExtendedService addressExtendedService;

    @Test
    void buildExtenderAddress() {
        // given
        Address address = DomainFixtures.someAddress1();
        AddressExtended addressExtended = DomainFixtures.someAddressExtended1(address);
        Restaurant restaurant = DomainFixtures.someRestaurant3(addressExtended);

        // when
        AddressExtended result = addressExtendedService.buildExtenderAddress(restaurant);

        // then
        Assertions.assertThat(result.getStreetNumber()).isEqualTo(addressExtended.getStreetNumber());
    }
}