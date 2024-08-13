package tech.saintbassanaga.stockmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.saintbassanaga.stockmanager.models.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
    Optional<List<Supplier>> findByDesignationContaining(String text);
    Optional<List<Supplier>> findSuppliersByAddress_City(String city);
}