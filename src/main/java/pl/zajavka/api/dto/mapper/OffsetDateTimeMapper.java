package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OffsetDateTimeMapper {

    DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Named("mapOffsetDateTimeToString")
    default String mapOffsetDateTimeToString(OffsetDateTime offsetDateTime) {
        return Optional.ofNullable(offsetDateTime)
            .map(odt -> offsetDateTime.atZoneSameInstant(ZoneId.systemDefault()))
            .map(odt -> odt.format(DATE_FORMAT))
            .orElse(null);
    }
}
