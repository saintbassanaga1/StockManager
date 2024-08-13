package tech.saintbassanaga.stockmanager.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link tech.saintbassanaga.stockmanager.models.Supplier}
 */
public record CreateSupplierDto(String designation, String Description, @NotNull ContactDto contact,
                                @NotNull(message = "Address must not be null") AddressDto address) implements Serializable {
}