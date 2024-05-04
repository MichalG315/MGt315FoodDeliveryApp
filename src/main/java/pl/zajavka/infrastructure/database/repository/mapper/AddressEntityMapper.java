package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.domain.Address;
import pl.zajavka.infrastructure.database.entity.AddressEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressEntityMapper {


    AddressEntity mapToEntity(Address address);

    Address mapFromEntity(AddressEntity addressEntity);
}
