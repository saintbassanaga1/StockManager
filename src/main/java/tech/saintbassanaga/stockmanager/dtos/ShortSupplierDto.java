package tech.saintbassanaga.stockmanager.dtos;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link tech.saintbassanaga.stockmanager.models.Supplier}
 */
public record ShortSupplierDto(UUID uuid, String designation, String Description,
                               ContactDto contact) implements Serializable {
}