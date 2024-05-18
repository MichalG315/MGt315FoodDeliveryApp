package pl.zajavka.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    @NotNull
    @NotEmpty
    private String country;
    @NotNull
    @NotEmpty
    private String city;
    @NotNull
    @NotEmpty
    private String postalCode;
    @NotNull
    @NotEmpty
    private String streetName;
    private String streetNumber;
}
