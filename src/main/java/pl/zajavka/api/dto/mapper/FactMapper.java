package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.api.dto.FactDTO;
import pl.zajavka.domain.Fact;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FactMapper {
    FactDTO mapToDTO(Fact randomCatFact);
}
