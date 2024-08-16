package tech.saintbassanaga.stockmanager.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.saintbassanaga.stockmanager.config.exceptions.ErrorResponse;
import tech.saintbassanaga.stockmanager.dtos.CreateSupplierDto;
import tech.saintbassanaga.stockmanager.dtos.ShortSupplierDto;
import tech.saintbassanaga.stockmanager.models.Supplier;
import tech.saintbassanaga.stockmanager.services.SupplierService;

import java.util.List;
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
@RestController
@AllArgsConstructor
@RequestMapping("api/suppliers/")
public class SupplierController {

    private final SupplierService supplierService;

    /**
     * @param dto "Supplier Data to be persisted to DataBase"
     * @return "an ResponseEntity of created Supplier"
     */
    @Operation(summary = "Create a new supplier", description = "Adds a new supplier to the system")
    @ApiResponse(responseCode = "200", description = "Successfully Created Supplier",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ShortSupplierDto.class))
    )
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Supplier> createSupplier(@RequestBody CreateSupplierDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.createSupplier(dto));
    }

    /**
     * @param uuid "Supplier ID"
     * @return "An ResponseEntity of Found Supplier or just an Error message if not"
     */
    @Operation(summary = "Get Supplier by UUID", description = "Retrieve supplier details using UUID")
    @ApiResponse(responseCode = "302", description = "Successfully retrieved Supplier"
            , content = @Content(mediaType = "application/json"
            , schema = @Schema(implementation = ShortSupplierDto.class))
    )
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = "/{uuid}")
    public ResponseEntity<ShortSupplierDto> getSupplierByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.status(HttpStatus.FOUND).body(supplierService.findSupplierById(uuid));
    }

    @Operation(summary = "Find All supplier", description = " This method is used to find all data about a short supplier present in th database")
    @ApiResponse(responseCode = "302", description = "All suppliers have been loaded successfully !",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShortSupplierDto.class))
    )
    @GetMapping("/")
    public ResponseEntity<List<ShortSupplierDto>> getAllSuppliers() {
        return ResponseEntity.status(HttpStatus.FOUND).body(supplierService.findAllSuppliers());
    }

    @Operation(summary = "Find supplier in city", description = "This method find supplier logged in a specifics city")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "All suppliers have been loaded successfully!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShortSupplierDto.class))
            ),
            @ApiResponse(responseCode = "404", description = "No supplier found for the given city",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/city/{city}")
    public ResponseEntity<List<ShortSupplierDto>> getAllSuppliersByCity(@PathVariable("city") String city) {
        return ResponseEntity.status(HttpStatus.FOUND).body(supplierService.findSupplierByCity(city));
    }




}
