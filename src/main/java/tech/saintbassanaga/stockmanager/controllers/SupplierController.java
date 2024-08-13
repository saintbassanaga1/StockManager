package tech.saintbassanaga.stockmanager.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tech.saintbassanaga.stockmanager.dtos.ShortSupplierDto;
import tech.saintbassanaga.stockmanager.services.SupplierService;

import java.util.UUID;

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
@RestController()
@AllArgsConstructor
@RequestMapping(name = "supplier",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SupplierController {
    private final SupplierService supplierService;

    @ApiOperation(value = "Get Supplier by UUID", notes = "Retrieve supplier details using UUID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Supplier details retrieved successfully", response = ShortSupplierDto.class),
            @ApiResponse(code = 404, message = "Supplier not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShortSupplierDto getSupplierByUuid(@PathVariable UUID uuid) {
        return supplierService.findSupplierById(uuid);
    }
}
