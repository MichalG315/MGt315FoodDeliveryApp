package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.AddressExtendedDAO;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.infrastructure.database.entity.AddressExtendedEntity;
import pl.zajavka.infrastructure.database.repository.jpa.AddressExtendedJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.AddressExtendedEntityMapper;

@Repository
@AllArgsConstructor
public class AddressExtendedRepository implements AddressExtendedDAO {

    private final AddressExtendedJpaRepository addressExtendedJpaRepository;
    private final AddressExtendedEntityMapper addressExtendedEntityMapper;

    @Override
    public AddressExtended saveAddressExtended(AddressExtended addressExtended) {
        AddressExtendedEntity toSave = addressExtendedEntityMapper.mapToEntity(addressExtended);
        AddressExtendedEntity saved = addressExtendedJpaRepository.save(toSave);
        return addressExtendedEntityMapper.mapFromEntity(saved);
    }
}
