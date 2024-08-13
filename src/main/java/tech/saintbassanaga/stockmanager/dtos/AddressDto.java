package tech.saintbassanaga.stockmanager.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link tech.saintbassanaga.stockmanager.models.embedded.Address}
 */
public record AddressDto(String city, String state, @NotNull(message = "Country cannot be null") String country,
                         String street, String firstAddress, String secondAddress,
                         String thirdAddress) implements Serializable {
}