package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.infrastructure.database.entity.AddressExtendedEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressExtendedEntityMapper {

    AddressExtendedEntity mapToEntity(AddressExtended addressExtended);

    AddressExtended mapFromEntity(AddressExtendedEntity addressExtendedEntity);
}
