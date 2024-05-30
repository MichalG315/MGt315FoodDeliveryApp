package pl.zajavka.api.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.FactDTO;
import pl.zajavka.domain.Fact;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T11:08:20+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class FactMapperImpl implements FactMapper {

    @Override
    public FactDTO mapToDTO(Fact randomCatFact) {
        if ( randomCatFact == null ) {
            return null;
        }

        FactDTO.FactDTOBuilder factDTO = FactDTO.builder();

        factDTO.fact( randomCatFact.getFact() );

        return factDTO.build();
    }
}
