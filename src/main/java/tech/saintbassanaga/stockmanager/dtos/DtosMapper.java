package tech.saintbassanaga.stockmanager.dtos;

import jakarta.validation.constraints.NotNull;
import tech.saintbassanaga.stockmanager.models.Product;
import tech.saintbassanaga.stockmanager.models.Supplier;
import tech.saintbassanaga.stockmanager.models.embedded.Address;
import tech.saintbassanaga.stockmanager.models.embedded.Contact;

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
public class DtosMapper {
    static ContactDto fromContactEntity(Contact contact) {
        return new ContactDto(contact.getEmail(), contact.getPhone(), contact.getPostalCode(), contact.getFax());
    }
    static Contact toContactEntity(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setEmail(contactDto.email());
        contact.setPhone(contactDto.phone());
        contact.setPostalCode(contactDto.postalCode());
        contact.setFax(contactDto.fax());
        return contact;
    }
    AddressDto fromAddressEntity(Address address) {
        return new AddressDto(address.getCity(), address.getState(), address.getCountry(), address.getStreet(), address.getFirstAddress(), address.getSecondAddress(), address.getThirdAddress());
    }
    public static ShortSupplierDto fromEntity(Supplier supplier) {
        return new ShortSupplierDto(supplier.getUuid(), supplier.getDesignation(), supplier.getDescription(),fromContactEntity(supplier.getContact()));
    }
    FullSupplierDto fromLongSupplierEntity(Supplier supplier) {
        return new FullSupplierDto(supplier.getUuid(), supplier.getDesignation(), supplier.getDescription(), fromContactEntity(supplier.getContact()),fromAddressEntity(supplier.getAddress()),
                supplier.getProducts().stream()
                .map(this::fromProductEntity)
                .collect(Collectors.toList())
        );
    }
    ShowProductDto fromProductEntity(Product product) {
        return new ShowProductDto(product.getUuid(), product.getName(), product.getDescription(),product.getPrice(),product.getStatus());
    }

    FullProductDto fromFullProductEntity(Product product) {
        return new FullProductDto(product.getUuid(), product.getName(), product.getDescription(), product.getPrice(),product.getStatus(),fromEntity(product.getSupplier()));
    }

    public static Supplier toEntity( CreateSupplierDto createSupplierDto) {
        Supplier supplier = new Supplier();
        supplier.setDesignation(createSupplierDto.designation());
        supplier.setDescription(createSupplierDto.Description());
        supplier.setContact(toContactEntity(createSupplierDto.contact()));
        supplier.setAddress(toAddressEntity(createSupplierDto.address()));
        return supplier;
    }

    private static Address toAddressEntity(@NotNull(message = "Address must not be null") AddressDto address) {
        Address addressEntity = new Address();
        addressEntity.setFirstAddress(address.firstAddress());
        addressEntity.setSecondAddress(address.secondAddress());
        addressEntity.setThirdAddress(address.thirdAddress());
        addressEntity.setCity(address.city());
        addressEntity.setState(address.state());
        addressEntity.setCountry(address.country());
        addressEntity.setStreet(address.street());
        return addressEntity;
    }
}
