package tech.saintbassanaga.stockmanager.services.impls;

import org.springframework.stereotype.Service;
import tech.saintbassanaga.stockmanager.config.exceptions.SupplierNotFoundException;
import tech.saintbassanaga.stockmanager.dtos.CreateSupplierDto;
import tech.saintbassanaga.stockmanager.dtos.DtosMapper;
import tech.saintbassanaga.stockmanager.dtos.ShortSupplierDto;
import tech.saintbassanaga.stockmanager.models.Supplier;
import tech.saintbassanaga.stockmanager.repositories.SupplierRepository;
import tech.saintbassanaga.stockmanager.services.SupplierService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/*
 * MIT License
 *
 * Copyright (c) 2024 saintbassanaga
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@Service


























































public class SupplierServiceImpls implements SupplierService {

    private final SupplierRepository supplierRepository;
    public SupplierServiceImpls(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    @Override
    public List<ShortSupplierDto> findSupplierByName(String supplierName) {
        return supplierRepository.findByDesignationContaining(supplierName)
                .orElse(List.of())
                .stream()
                .map(DtosMapper::fromEntity)
                .toList();
    }

    @Override
    public List<ShortSupplierDto> findAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(DtosMapper::fromEntity)
                .toList();
    }

    @Override
    public ShortSupplierDto findSupplierById(UUID supplierId) {
        return supplierRepository.findById(supplierId)
                .map(DtosMapper::fromEntity)
                .orElseThrow(
                        ()-> new SupplierNotFoundException("Supplier Not Found with Id: " + supplierId)
                );
    }

    @Override
    public void deleteSupplierById(UUID supplierId) {
        supplierRepository.deleteById(supplierId);
    }

    @Override
    public Supplier createSupplier(CreateSupplierDto supplierDto) {
        return supplierRepository.save(DtosMapper.toEntity(supplierDto)) ;
    }


    @Override
    public ShortSupplierDto updateSupplier(UUID uuid, CreateSupplierDto supplierDto) {
        Optional<Supplier> supplier = supplierRepository.findById(uuid);
        if (supplier.isPresent()) {
            supplierRepository.save(DtosMapper.toEntity(supplierDto));
        }
        return supplier.map(DtosMapper::fromEntity).orElseThrow(()-> new SupplierNotFoundException("Supplier Not Found with Id" + uuid));
    }

    @Override
    public List<ShortSupplierDto> findSupplierByCity(String city) {
        // Retrieve suppliers by city, map to DTOs, and handle absence
        return supplierRepository.findSuppliersByAddress_City(city)
                .map(suppliers -> suppliers.stream()
                        .map(DtosMapper::fromEntity)
                        .collect(Collectors.toList()))
                .filter(dtoList -> !dtoList.isEmpty())
                .orElseThrow(() -> new SupplierNotFoundException("Supplier not found in the city: " + city));
    }
}
