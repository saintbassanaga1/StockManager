package tech.saintbassanaga.stockmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.saintbassanaga.stockmanager.models.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<List<Product>> findProductsByPriceBetween(BigDecimal lower, BigDecimal upper);
    Optional<List<Product>> findProductsByPriceLessThan(BigDecimal lower);
    Optional<List<Product>> findProductsByNameContaining(String name);
    Optional<Product> findProductByUuid(UUID uuid);
    Optional<List<Product>> findProductsBySupplier_Uuid(UUID uuid);
}