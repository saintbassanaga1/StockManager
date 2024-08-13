package tech.saintbassanaga.stockmanager.dtos;

import tech.saintbassanaga.stockmanager.models.embedded.ProductStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link tech.saintbassanaga.stockmanager.models.Supplier}
 */
public record FullSupplierDto(UUID uuid, String designation, String Description, ContactDto contact, AddressDto address,
                              List<ShowProductDto> products) implements Serializable {
}