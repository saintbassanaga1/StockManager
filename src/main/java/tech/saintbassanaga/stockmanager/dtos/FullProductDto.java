package tech.saintbassanaga.stockmanager.dtos;

import tech.saintbassanaga.stockmanager.models.embedded.ProductStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link tech.saintbassanaga.stockmanager.models.Product}
 */
public record FullProductDto(UUID uuid, String name, String description, BigDecimal price, ProductStatus status,
                             ShortSupplierDto supplier) implements Serializable {
}