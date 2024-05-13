package pl.zajavka.infrastructure.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.infrastructure.database.entity.AddressExtendedEntity;
import pl.zajavka.infrastructure.database.repository.jpa.AddressExtendedJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.AddressExtendedEntityMapper;
import pl.zajavka.infrastructure.database.repository.util.DomainFixtures;
import pl.zajavka.infrastructure.database.repository.util.EntityFixtures;

import static pl.zajavka.infrastructure.database.repository.util.DomainFixtures.*;
import static pl.zajavka.infrastructure.database.repository.util.DomainFixtures.someAddressExtended1;


@ExtendWith(MockitoExtension.class)
class AddressExtendedRepositoryMockitoTest {

    @Mock
    private AddressExtendedJpaRepository addressExtendedJpaRepository;

    @Mock
    private AddressExtendedEntityMapper addressExtendedEntityMapper;

    @InjectMocks
    private AddressExtendedRepository addressExtendedRepository;

    @Test
    void saveAddressExtended() {
        // given
        AddressExtended addressExtended = someAddressExtended1(someAddress1());
        AddressExtendedEntity addressExtendedEntity = EntityFixtures
                .someAddressExtended1(EntityFixtures.someAddress1());
        Mockito.when(addressExtendedEntityMapper.mapToEntity(addressExtended)).thenReturn(addressExtendedEntity);
        Mockito.when(addressExtendedEntityMapper.mapFromEntity(addressExtendedEntity)).thenReturn(addressExtended);
        Mockito.when(addressExtendedJpaRepository.saveAndFlush(addressExtendedEntity))
                .thenReturn(addressExtendedEntity);

        // when
        AddressExtended result = addressExtendedRepository.saveAddressExtended(addressExtended);

        // then
        Assertions.assertThat(result).isEqualTo(addressExtended);
    }
}