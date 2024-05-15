package pl.zajavka.infrastructure.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.domain.Address;
import pl.zajavka.infrastructure.database.entity.AddressEntity;
import pl.zajavka.infrastructure.database.repository.jpa.AddressJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.AddressEntityMapper;
import pl.zajavka.util.EntityFixtures;

import static pl.zajavka.util.DomainFixtures.someAddress1;

@ExtendWith(MockitoExtension.class)
class AddressRepositoryMockitoTest {
    @Mock
    private AddressJpaRepository addressJpaRepository;

    @Mock
    private AddressEntityMapper addressEntityMapper;

    @InjectMocks
    private AddressRepository addressRepository;

    @Test
    void saveAddress() {
        // given
        Address address = someAddress1();
        AddressEntity addressEntity = EntityFixtures
                .someAddress1();
        Mockito.when(addressEntityMapper.mapToEntity(address)).thenReturn(addressEntity);
        Mockito.when(addressEntityMapper.mapFromEntity(addressEntity)).thenReturn(address);
        Mockito.when(addressJpaRepository.saveAndFlush(addressEntity)).thenReturn(addressEntity);

        // when
        Address result = addressRepository.saveAddress(address);

        // then
        Assertions.assertThat(result).isEqualTo(address);
    }
}