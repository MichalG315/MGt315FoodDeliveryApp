package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.api.dto.mapper.AddressMapper;
import pl.zajavka.business.dao.AddressDAO;
import pl.zajavka.domain.Address;
import pl.zajavka.infrastructure.database.entity.AddressEntity;
import pl.zajavka.infrastructure.database.repository.jpa.AddressJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.AddressEntityMapper;

@Repository
@AllArgsConstructor
public class AddressRepository implements AddressDAO {

    private final AddressJpaRepository addressJpaRepository;
    private final AddressEntityMapper addressEntityMapper;

    @Override
    public Address saveAddress(Address address) {
        AddressEntity toSave = addressEntityMapper.mapToEntity(address);
        AddressEntity saved = addressJpaRepository.save(toSave);
        return addressEntityMapper.mapFromEntity(saved);
    }
}
