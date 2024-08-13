package tech.saintbassanaga.stockmanager.dtos;

import java.io.Serializable;

/**
 * DTO for {@link tech.saintbassanaga.stockmanager.models.embedded.Contact}
 */
public record ContactDto(String email, String phone, String postalCode, String fax) implements Serializable {
}